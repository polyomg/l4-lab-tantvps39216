package com.poly.Lab5_PS39216.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.Lab5_PS39216.Service.ShoppingCartService;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService cart;

    @RequestMapping("/view")
    public String view(Model model) {
        model.addAttribute("cart",cart);
        return "cart/index";
    }

    @RequestMapping("/add/{id}")
    public String add(@PathVariable("id") Integer id) {
        cart.add(id);
        return "redirect:/cart/view";
    }

    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {
        cart.remove(id);
        return "redirect:/cart/view";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, @RequestParam("qty") Integer qty) {
        cart.update(id, qty);
        return "redirect:/cart/view";
    }

    @RequestMapping("/clear")
    public String clear() {
        cart.clear();
        return "redirect:/cart/view";
    }
}