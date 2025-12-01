package com.example.project3.controller;

import com.example.project3.entity.Product;
import com.example.project3.repository.ProductRepository;
import com.example.project3.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired private ProductRepository productRepo;
    @Autowired private CartService cartService;

    // Trang chủ (Xử lý cả Tìm kiếm & Lọc)
    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "category", required = false) String category) {

        List<Product> products;

        if (keyword != null && !keyword.isEmpty()) {
            // 1. Nếu có từ khóa -> Tìm theo tên
            products = productRepo.findByNameContainingIgnoreCase(keyword);
            model.addAttribute("keyword", keyword);
        } else if (category != null && !category.isEmpty()) {
            // 2. Nếu có danh mục -> Lọc theo danh mục (Cái bạn đang thiếu)
            products = productRepo.findByCategoryIgnoreCase(category);
            model.addAttribute("category", category);
        } else {
            // 3. Mặc định -> Lấy tất cả
            products = productRepo.findAll();
        }

        model.addAttribute("products", products);
        model.addAttribute("cartCount", cartService.getItems().size());

        return "index";
    }

    // Trang chi tiết
    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        model.addAttribute("p", productRepo.findById(id).orElse(null));
        model.addAttribute("cartCount", cartService.getItems().size());
        return "detail";
    }
}