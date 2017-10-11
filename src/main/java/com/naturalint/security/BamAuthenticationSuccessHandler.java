package com.naturalint.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by skn on 10/11/2017.
 */
public class BamAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        response.addCookie(new Cookie("X-Auth-Login", new String(Base64.encode(principal.getUsername().getBytes()))));
        response.addCookie(new Cookie("X-Auth-Password", new String(Base64.encode(authentication.getCredentials().toString().getBytes()))));
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
