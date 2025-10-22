package com.poly.Lab5_PS39216.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final HttpSession session;

    public SessionService(HttpSession session) {
        this.session = session;
    }

    // Lưu giá trị vào session
    public void set(String key, Object value) {
        session.setAttribute(key, value);
    }

    // Lấy giá trị từ session
    public Object get(String key) {
        return session.getAttribute(key);
    }

    // Xóa giá trị trong session
    public void remove(String key) {
        session.removeAttribute(key);
    }
}
