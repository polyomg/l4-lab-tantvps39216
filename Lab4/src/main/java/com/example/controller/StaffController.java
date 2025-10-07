package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @RequestMapping("/create/form")
    public String createForm(Model model) {
        model.addAttribute("staff", new Staff()); // Khởi tạo đối tượng Staff
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "/staff-create"; // Không cần dấu "/"
    }

    @RequestMapping("/create/save")
    public String createSave(Model model, @RequestPart("photo_file") MultipartFile photoFile, @Valid @ModelAttribute("staff") Staff staff, Errors errors) {
        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename()); // Lấy tên file ảnh
        }

        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
        } else {
            model.addAttribute("message", "Dữ liệu đã nhập đúng!");
        }
        return "/staff-validate"; // Trả về giao diện xác nhận
    }
}


