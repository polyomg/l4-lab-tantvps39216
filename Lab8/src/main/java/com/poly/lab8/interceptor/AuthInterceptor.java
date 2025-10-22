package com.poly.Lab8.interceptor;

import com.poly.Lab8.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();

        // Lấy user từ session
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            // Lưu lại URI user đang muốn truy cập
            session.setAttribute("securityUri", uri);
            response.sendRedirect("/auth/login");
            return false;
        }

        // Đã đăng nhập, kiểm tra vai trò admin
        if (uri.startsWith("/admin") && !user.isAdmin()) {
            // Lưu lại URI (mặc dù có thể không cần thiết nếu chỉ redirect về login)
            session.setAttribute("securityUri", uri);
            response.sendRedirect("/auth/login"); // Hoặc trang "Access Denied"
            return false;
        }

        return true;
    }
}