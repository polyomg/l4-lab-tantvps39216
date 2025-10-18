package com.poly.Lab5_PS39216.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Lab5_PS39216.Service.CookieService;
import com.poly.Lab5_PS39216.Service.ParamService;
import com.poly.Lab5_PS39216.Service.SessionService;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private CookieService cookieService;

    @Autowired
    private ParamService paramService;

    @Autowired
    private SessionService sessionService;

    // Hiển thị trang đăng nhập
    @GetMapping("/login")
    public String loginForm(Model model) {
        // Kiểm tra nếu có cookie thì điền sẵn username
        String rememberedUser = cookieService.getValue("user");
        model.addAttribute("username", rememberedUser != null ? rememberedUser : "");
        return "login";
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String loginProcess(Model model) {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);

        if ("poly".equals(username) && "123".equals(password)) {
            // Lưu username vào session
            sessionService.set("username", username);

            // Ghi nhớ tài khoản
            if (remember) {
                cookieService.add("user", username, 10); // Lưu 10 ngày
            } else {
                cookieService.remove("user");
            }

            model.addAttribute("message", "Đăng nhập thành công!");
        } else {
            model.addAttribute("message", "Sai tài khoản hoặc mật khẩu!");
        }

        return "login";
    }
}