package config.authentication;

import dao.content.HvssUserDao;
import dao.content.HvssUserTokenDao;
import entities.user.HvssUser;
import entities.user.HvssUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import services.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class AuthorizationFilter extends HttpFilter {

    @Autowired
    UserService userService;

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userAuthToken = servletRequest.getHeader("HVSS-X-AUTH-TOKEN");
        String userSessionToken = servletRequest.getHeader("HVSS-X-SESSION-TOKEN");

        if(
            (!servletRequest.getRequestURI().equals("/user/register") && !servletRequest.getRequestURI().equals("/user/authenticate") &&
                    !servletRequest.getRequestURI().equals("/user/auth/refresh") &&  !servletRequest.getRequestURI().startsWith("/node") &&
            (userAuthToken == null || userAuthToken.isEmpty()) && userService.getUserByToken(userAuthToken, userSessionToken) == null)
        ){
            servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
