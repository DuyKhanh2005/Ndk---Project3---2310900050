package com.example.project3.controller;

import com.example.project3.entity.Product;
import com.example.project3.entity.Voucher; // Đã có
import com.example.project3.repository.OrderRepository;
import com.example.project3.repository.ProductRepository;
import com.example.project3.repository.UserRepository;
import com.example.project3.repository.VoucherRepository; // Đã có
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; // Hỗ trợ upload ảnh

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private ProductRepository productRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private OrderRepository orderRepo;
    @Autowired private VoucherRepository voucherRepo; // <--- MỚI THÊM

    // 1. QUẢN LÝ SẢN PHẨM
    @GetMapping("")
    public String index(Model model, @RequestParam(required = false) String keyword) {
        if (keyword != null) model.addAttribute("products", productRepo.findByNameContainingIgnoreCase(keyword));
        else model.addAttribute("products", productRepo.findAll());
        return "admin/product_list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product_form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepo.findById(id).orElse(null));
        return "admin/product_form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product,
                       @RequestParam(value = "imageFile", required = false) MultipartFile file) throws IOException {
        // Upload ảnh từ máy tính
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/images/" + fileName);
            Files.write(path, file.getBytes());
            product.setImage("/images/" + fileName);
        } else if (product.getImage() == null || product.getImage().isEmpty()) {
            product.setImage("https://placehold.co/400"); // Ảnh mặc định
        }
        productRepo.save(product);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productRepo.deleteById(id);
        return "redirect:/admin";
    }

    // 2. QUẢN LÝ KHÁCH HÀNG
    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "admin/customer_list";
    }

    // 3. QUẢN LÝ VOUCHER (MỚI THÊM VÀO ĐÂY)
    @GetMapping("/vouchers")
    public String voucherList(Model model) {
        model.addAttribute("vouchers", voucherRepo.findAll());
        model.addAttribute("newVoucher", new Voucher()); // Để dùng cho form thêm mới
        return "admin/voucher_list";
    }

    @PostMapping("/vouchers/save")
    public String saveVoucher(@ModelAttribute Voucher voucher) {
        voucherRepo.save(voucher);
        return "redirect:/admin/vouchers";
    }

    @GetMapping("/vouchers/delete/{id}")
    public String deleteVoucher(@PathVariable Long id) {
        voucherRepo.deleteById(id);
        return "redirect:/admin/vouchers";
    }

    // 4. THỐNG KÊ (STATS)
    @GetMapping("/stats")
    public String stats(Model model) {
        model.addAttribute("totalProducts", productRepo.count());
        model.addAttribute("totalUsers", userRepo.count());
        model.addAttribute("totalOrders", orderRepo.count());

        List<Integer> revenueData = Arrays.asList(15000000, 23000000, 18000000, 35000000, 28000000, 42000000);
        List<String> months = Arrays.asList("Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6");

        model.addAttribute("revenueData", revenueData);
        model.addAttribute("months", months);

        return "admin/stats";
    }
}