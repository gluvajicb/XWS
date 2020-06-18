package xmlproject.be.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;
import xmlproject.be.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class used in HttpSecurity configuration for filtering requests to the server.
 * Class extends OncePerRequestFilter and overrides doFilterInternal method.
 * Overridden method will check if request was made against paths marked to be ignored by the filter,
 * and skip user check for such requests. If request was made with authentication details, such will be checked, and request will be propagated further.
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    UserService userService;

    /*
     * The below paths will get ignored by the filter
     */
    public static final String ROOT_MATCHER = "/";
    public static final String FAVICON_MATCHER = "/favicon.ico";
    public static final String HTML_MATCHER = "/**/*.html";
    public static final String CSS_MATCHER = "/**/*.css";
    public static final String JS_MATCHER = "/**/*.js";
    public static final String IMG_MATCHER = "/images/*";
    public static final String LOGOUT_MATCHER = "/logout";

    private List<String> pathsToSkip = Arrays.asList(
        ROOT_MATCHER,
        HTML_MATCHER,
        FAVICON_MATCHER,
        CSS_MATCHER,
        JS_MATCHER,
        IMG_MATCHER,
        LOGOUT_MATCHER
    );

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String authToken = tokenHelper.getToken(request);

        if (authToken != null && !skipPathRequest(request, pathsToSkip)) {
            try {
                String username = tokenHelper.getUsernameFromToken(authToken);
                UserDetails userDetails = userService.findByUsername(username);

                if (userDetails != null && userDetails.isEnabled()) {
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * Check if request was made against path that is marked to be skipped for user authentication check.
     *
     * @param request User request to the server.
     * @param pathsToSkip Paths marked to be skipped for user authentication check.
     * @return <code>true</code> if request matches any of the paths marked to be skipped for user authentication check, <code>false</code> otherwise.
     */
    private boolean skipPathRequest(HttpServletRequest request, List<String> pathsToSkip) {
        Assert.notNull(pathsToSkip, "path cannot be null.");
        List<RequestMatcher> m = pathsToSkip
                .stream()
                .map(path -> new AntPathRequestMatcher(path))
                .collect(Collectors.toList());

        OrRequestMatcher matchers = new OrRequestMatcher(m);

        return matchers.matches(request);
    }

}
