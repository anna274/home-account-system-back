package by.bsuir.rusakovich.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandlerImpl extends RememberMeAuthenticationFilter {
    public AuthenticationSuccessHandlerImpl(AuthenticationManager authenticationManager, RememberMeServices rememberMeServices) {
        super(authenticationManager, rememberMeServices);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
        super.onSuccessfulAuthentication(request, response, authResult);
        try {
            request.getRequestDispatcher("/successful-login").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
