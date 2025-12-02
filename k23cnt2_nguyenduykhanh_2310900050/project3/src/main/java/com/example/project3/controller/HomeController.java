package com.example.project3.controller;

import com.example.project3.entity.Product;
import com.example.project3.entity.User;
import com.example.project3.repository.OrderRepository;
import com.example.project3.repository.ProductRepository;
import com.example.project3.repository.UserRepository;
import com.example.project3.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // Thêm 2 Repository này để lấy dữ liệu Lịch sử đơn hàng
    @Autowired private UserRepository userRepo;
    @Autowired private OrderRepository orderRepo;

    // 1. TRANG CHỦ (Hiển thị + Tìm kiếm + Lọc)
    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "category", required = false) String category) {

        List<Product> products;

        if (keyword != null && !keyword.isEmpty()) {
            // Tìm theo tên
            products = productRepo.findByNameContainingIgnoreCase(keyword);
            model.addAttribute("keyword", keyword);
        } else if (category != null && !category.isEmpty()) {
            // Lọc theo danh mục
            products = productRepo.findByCategoryIgnoreCase(category);
            model.addAttribute("category", category);
        } else {
            // Mặc định lấy hết
            products = productRepo.findAll();
        }

        model.addAttribute("products", products);
        model.addAttribute("cartCount", cartService.getItems().size()); // Số lượng giỏ hàng

        return "index";
    }

    // 2. TRANG CHI TIẾT SẢN PHẨM
    @GetMapping("/product/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product p = productRepo.findById(id).orElse(null);
        if (p == null) return "redirect:/"; // Nếu id sai thì về trang chủ

        model.addAttribute("p", p);
        model.addAttribute("cartCount", cartService.getItems().size());
        return "detail";
    }

    // 3. TRANG LỊCH SỬ ĐƠN HÀNG (QUAN TRỌNG: Để nút Lịch sử hoạt động)
    @GetMapping("/profile/orders")
    public String orderHistory(Model model) {
        // Lấy User đang đăng nhập từ hệ thống bảo mật
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username);

        if (user != null) {
            // Lấy danh sách đơn hàng của User này (Mới nhất lên đầu)
            model.addAttribute("orders", orderRepo.findByUserOrderByCreateAtDesc(user));
        }

        model.addAttribute("cartCount", cartService.getItems().size());
        return "history"; // Trả về file history.html
    }
}