package xmlproject.be.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class used in HttpSecurity configuration for handling requests from unauthenticated users.
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    /**
     * Method is invoked when user tries to access a secured REST resource without supplying any credentials.
     * Response will respond with a 401 UnAuthorized response.
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }

}