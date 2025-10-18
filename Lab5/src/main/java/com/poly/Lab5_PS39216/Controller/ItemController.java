package com.poly.Lab5_PS39216.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Lab5_PS39216.entitys.DB;


@Controller
public class ItemController {
    @RequestMapping("/item/index")
    public String list (Model model) {
        model.addAttribute("items", DB.items.values());
        return "item/index";
    }
}
