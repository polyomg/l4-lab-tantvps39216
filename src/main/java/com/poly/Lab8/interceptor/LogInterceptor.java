package com.poly.Lab8.interceptor;

import com.poly.Lab8.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Date;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        // Lấy user từ session
        Account user = (Account) request.getSession().getAttribute("user");

        String fullname = "Guest"; //
        if (user != null) {
            fullname = user.getFullname();
        }

        // Ghi log ra console
        System.out.println("LogInterceptor: "
                + request.getRequestURI()
                + ", " + new Date()
                + ", " + fullname);

        return true;
    }
}