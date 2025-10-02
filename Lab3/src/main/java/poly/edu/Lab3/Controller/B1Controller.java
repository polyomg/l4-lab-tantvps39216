package poly.edu.Lab3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class B1Controller {

    @RequestMapping("/detail")
    public String detai(Model model){
        Staff staff = Staff.builder().id("user@gmail.com").fullname("Nguyen Van user").level(2).build();
        model.addAttribute("staff", staff);
        return "/demo/staff-detail";
    }
}
