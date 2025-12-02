package com.example.project3.controller;

import com.example.project3.entity.User;
import com.example.project3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepo; // Đảm bảo UserRepository đã được tạo đúng

    // Xử lý GET: Hiển thị form đăng ký
    @GetMapping
    public String showRegistrationForm(Model model) {
        // Model phải có đối tượng user rỗng để form hoạt động
        model.addAttribute("user", new User());
        return "register"; // <--- TRẢ VỀ register.html
    }

    // Xử lý POST: Nhận dữ liệu và lưu user
    @PostMapping
    public String registerUser(@ModelAttribute User user) {
        user.setRole("USER"); // Gán vai trò mặc định
        user.setActive(true);
        userRepo.save(user);

        // Đăng ký xong chuyển về chính trang đăng ký kèm thông báo thành công
        return "redirect:/register?success";
    }
}