package com.example.project3.controller;
import com.example.project3.entity.*;
import com.example.project3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

@Controller @RequestMapping("/admin")
public class AdminController {
    @Autowired private ProductRepository productRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private OrderRepository orderRepo;
    @Autowired private VoucherRepository voucherRepo;

    // PRODUCT
    @GetMapping("") public String idx(Model m, @RequestParam(required=false) String keyword) {
        m.addAttribute("products", keyword!=null ? productRepo.findByNameContainingIgnoreCase(keyword) : productRepo.findAll());
        return "admin/product_list";
    }
    @GetMapping("/create") public String create(Model m) { m.addAttribute("product", new Product()); return "admin/product_form"; }
    @GetMapping("/edit/{id}") public String edit(@PathVariable Long id, Model m) { m.addAttribute("product", productRepo.findById(id).orElse(null)); return "admin/product_form"; }
    @PostMapping("/save") public String save(@ModelAttribute Product p, @RequestParam(required=false) MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String f = imageFile.getOriginalFilename();
            Files.write(Paths.get("src/main/resources/static/images/" + f), imageFile.getBytes());
            p.setImage("/images/" + f);
        } else if (p.getImage() == null) p.setImage("https://placehold.co/400");
        productRepo.save(p); return "redirect:/admin";
    }
    @GetMapping("/delete/{id}") public String del(@PathVariable Long id) { productRepo.deleteById(id); return "redirect:/admin"; }

    // USER
    @GetMapping("/users") public String users(Model m) { m.addAttribute("users", userRepo.findAll()); return "admin/customer_list"; }
    @GetMapping("/users/create") public String uCreate(Model m) { m.addAttribute("user", new User()); return "admin/customer_form"; }
    @GetMapping("/users/edit/{id}") public String uEdit(@PathVariable Long id, Model m) { m.addAttribute("user", userRepo.findById(id).orElse(null)); return "admin/customer_form"; }
    @PostMapping("/users/save") public String uSave(@ModelAttribute User u) {
        if(u.getId()!=null && (u.getPassword()==null || u.getPassword().isEmpty())) {
            User old = userRepo.findById(u.getId()).orElse(null);
            if(old!=null) u.setPassword(old.getPassword());
        }
        u.setActive(true); userRepo.save(u); return "redirect:/admin/users";
    }
    @GetMapping("/users/delete/{id}") public String uDel(@PathVariable Long id) { userRepo.deleteById(id); return "redirect:/admin/users"; }

    // VOUCHER & STATS
    @GetMapping("/vouchers") public String vouchers(Model m) { m.addAttribute("vouchers", voucherRepo.findAll()); m.addAttribute("newVoucher", new Voucher()); return "admin/voucher_list"; }
    @PostMapping("/vouchers/save") public String vSave(@ModelAttribute Voucher v) { voucherRepo.save(v); return "redirect:/admin/vouchers"; }
    @GetMapping("/vouchers/delete/{id}") public String vDel(@PathVariable Long id) { voucherRepo.deleteById(id); return "redirect:/admin/vouchers"; }

    @GetMapping("/stats") public String stats(Model m) {
        m.addAttribute("totalProducts", productRepo.count());
        m.addAttribute("totalUsers", userRepo.count());
        m.addAttribute("totalOrders", orderRepo.count());
        m.addAttribute("revenueData", Arrays.asList(15000000, 23000000, 18000000, 35000000, 28000000, 42000000));
        m.addAttribute("months", Arrays.asList("T1", "T2", "T3", "T4", "T5", "T6"));
        return "admin/stats";
    }
}