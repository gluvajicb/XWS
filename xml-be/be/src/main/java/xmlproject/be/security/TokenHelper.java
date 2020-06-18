package xmlproject.be.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Class for generating and processing JWT token.
 */
@Service
public class TokenHelper {

    @Value("${app.name}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    @Value("${jwt.cookie}")
    private String AUTH_COOKIE;

    @Qualifier("UserService")
    @Autowired
    UserDetailsService userDetailsService;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * Get user's username from provided JWT token.
     *
     * @param token JWT token.
     * @return User's usernames.
     */
    public String getUsernameFromToken(String token) {
        String username;

        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    /**
     * Generate JWT token for provided username.
     *
     * @param username User's username.
     * @return JWT token.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setIssuedAt(generateCurrentDate())
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }

    /**
     * Get claims from the JWT token.
     *
     * @param token JWT token
     * @return Claims from provided JWT token
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(this.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    /**
     * Generate JWT token from provided claims.
     *
     * @param claims Claims for JWT token.
     * @return JWT token.
     */
    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }

    /**
     * Check if JWT token can be refreshed. Token will be refreshed if it is valid and hasn't already expired.
     *
     * @param token JWT token
     * @return <code>true</code> if token can be refreshed, <code>false</code> otherwise.
     */
    public Boolean canTokenBeRefreshed(String token) {
        try {
            final Date expirationDate = getClaimsFromToken(token).getExpiration();

            return expirationDate.compareTo(generateCurrentDate()) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Refresh provided JWT token.
     *
     * @param token JWT token to be refreshed.
     * @return Refreshed JWT token.
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.setIssuedAt(generateCurrentDate());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }

        return refreshedToken;
    }

    /**
     * Get current date.
     *
     * @return Current date.
     */
    private Date generateCurrentDate() {
        return new Date();
    }

    /**
     * Generate expiration date for JWT token.
     *
     * @return Expiration date.
     */
    private Date generateExpirationDate() {
        return new Date((new Date()).getTime() + this.EXPIRES_IN * 1000);
    }

    /**
     * Get JWT token from Http request.
     *
     * @param request HttpServletRequest to the server.
     * @return JWT token.
     */
    public String getToken(HttpServletRequest request) {
        /**
         * Getting the token from Cookie store
         */
        Cookie authCookie = getCookieValueByName(request, AUTH_COOKIE);
        if (authCookie != null) {
            return authCookie.getValue();
        }

        /**
         * Getting the token from Authentication header e.g Bearer your_token
         */
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    /**
     * Find a specific HTTP cookie in a request.
     *
     * @param request The HTTP request object.
     * @param name The cookie name to look for.
     * @return The cookie, or <code>null</code> if not found.
     */
    public Cookie getCookieValueByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }

        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals(name)) {
                return request.getCookies()[i];
            }
        }

        return null;
    }
}
