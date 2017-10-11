package com.naturalint.security;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by skn on 10/11/2017.
 */
public class CookiesPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    private boolean exceptionIfHeaderMissing = true;

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        try {
            for (Cookie cookie : request.getCookies()) {
                if ("X-Auth-Login".equals(cookie.getName())) {
                    return new String(Base64.decode(cookie.getValue().getBytes()));
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        try {
            for (Cookie cookie : request.getCookies()) {
                if ("X-Auth-Password".equals(cookie.getName())) {
                    return new String(Base64.decode(cookie.getValue().getBytes()));
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void setExceptionIfHeaderMissing(boolean exceptionIfHeaderMissing) {
        this.exceptionIfHeaderMissing = exceptionIfHeaderMissing;
    }
}
