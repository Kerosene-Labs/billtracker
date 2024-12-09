package com.kerosenelabs.billtracker.service.supabasesdk;

import org.springframework.stereotype.Service;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class SupabaseCookieService {
    /**
     * Helper to get a cookie by name from a servlet request.
     * 
     * @throws AuthException
     */
    public Cookie getCookie(HttpServletRequest httpServletRequest, String name) throws AuthException {
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        throw new AuthException(String.format("Cookie for '%s' is not set", name));
    }

    /**
     * Helper to set a cookie on a servlet response. Please note that all cookies
     * are {@code HttpOnly}, {@code Secure} and are under the {@code /} resource.
     * This adds the cookie to the response for you.
     * 
     * @param httpServletResponse Servlet response
     * @return The newly created cookie
     */
    public Cookie setCookie(HttpServletResponse httpServletResponse, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        httpServletResponse.addCookie(cookie);
        return cookie;
    }
}
