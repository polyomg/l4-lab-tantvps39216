package com.poly.Lab5_PS39216.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public CookieService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void add(String name, String value, int days) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(days * 24 * 60 * 60); // Chuyển đổi ngày thành giây
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public String getValue(String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public void remove(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0); // Xóa cookie
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

