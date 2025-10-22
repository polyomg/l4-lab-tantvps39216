package com.poly.Lab8.Controller;

import com.poly.Lab8.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailService mailService;


    @GetMapping("/form")
    public String form(Model model) {
        // Do 'Mail' là inner class (class con) của MailService,
        // ta phải khởi tạo nó theo cách này
        MailService.Mail mail = MailService.Mail.builder().build();
        model.addAttribute("mail", mail);
        return "mail/form";
    }


    @PostMapping("/send-direct")
    public String sendDirect(Model model,
                             @ModelAttribute("mail") MailService.Mail mail) {
        try {
            mailService.send(mail);
            model.addAttribute("message", "Gửi mail thành công!");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi gửi mail: " + e.getMessage());
        }
        return "mail/form";
    }


    @PostMapping("/send-queue")
    public String sendQueue(Model model,
                            @ModelAttribute("mail") MailService.Mail mail) {
        mailService.push(mail);
        model.addAttribute("message", "Mail đã được xếp vào hàng đợi!");
        return "mail/form";
    }


}