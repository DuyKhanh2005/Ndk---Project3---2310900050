package com.example.project3.controller;
import com.example.project3.repository.ProductRepository;
import com.example.project3.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired private ProductRepository productRepo;
    @Autowired private CartService cartService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) String keyword, @RequestParam(required = false) String category) {
        if (keyword != null) model.addAttribute("products", productRepo.findByNameContainingIgnoreCase(keyword));
        else if (category != null) model.addAttribute("products", productRepo.findByCategoryIgnoreCase(category));
        else model.addAttribute("products", productRepo.findAll());

        model.addAttribute("cartCount", cartService.getItems().size());
        model.addAttribute("category", category);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("p", productRepo.findById(id).orElse(null));
        model.addAttribute("cartCount", cartService.getItems().size());
        return "detail";
    }
}