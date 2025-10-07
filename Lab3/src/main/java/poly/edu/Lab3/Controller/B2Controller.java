package poly.edu.Lab3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class B2Controller {

    @RequestMapping("/staff/list")
    public String list(Model model){
        List<Staff> list = List.of(
                Staff.builder().id("user1@gmail.com").fullname("Nguyễn văn user1").level(0).build(),
                Staff.builder().id("user2@gmail.com").fullname("Nguyễn văn user2").level(1).build(),
                Staff.builder().id("user3@gmail.com").fullname("Nguyễn văn user3").level(2).build(),
                Staff.builder().id("user4@gmail.com").fullname("Nguyễn văn user4").level(3).build(),
                Staff.builder().id("user5@gmail.com").fullname("Nguyễn văn user5").level(4).build(),
                Staff.builder().id("user6@gmail.com").fullname("Nguyễn văn user6").level(5).build());
                model.addAttribute("list", list);
                return "/demo/staff-list";
        }
    }

