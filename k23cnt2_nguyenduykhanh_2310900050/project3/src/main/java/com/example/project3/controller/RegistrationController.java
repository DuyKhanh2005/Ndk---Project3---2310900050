package com.example.project3.controller;
import com.example.project3.entity.User;
import com.example.project3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller @RequestMapping("/register")
public class RegistrationController {
    @Autowired private UserRepository userRepo;
    @GetMapping public String form(Model model) { model.addAttribute("user", new User()); return "register"; }
    @PostMapping public String save(@ModelAttribute User user) {
        user.setRole("USER"); user.setActive(true);
        userRepo.save(user); return "redirect:/register?success";
    }
}