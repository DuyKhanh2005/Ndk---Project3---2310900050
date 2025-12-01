package com.example.project3.controller;

import com.example.project3.entity.Order;
import com.example.project3.repository.OrderRepository;
import com.example.project3.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private CartService cartService;
    @Autowired private OrderRepository orderRepository;

    // 1. Xem giỏ hàng
    @GetMapping("")
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getTotal()); // Tổng tiền này đã trừ Voucher (nếu có)
        return "cart";
    }

    // 2. Thêm vào giỏ
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id) {
        cartService.addToCart(id);
        return "redirect:/cart";
    }

    // 3. Xóa khỏi giỏ
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        cartService.remove(id);
        return "redirect:/cart";
    }

    // --- LOGIC VOUCHER (MỚI THÊM) ---

    // 4. Áp dụng Voucher
    @PostMapping("/apply-voucher")
    public String applyVoucher(@RequestParam("code") String code) {
        // Gọi hàm xử lý trong Service
        cartService.applyVoucher(code);
        return "redirect:/cart";
    }

    // 5. Hủy Voucher
    @GetMapping("/remove-voucher")
    public String removeVoucher() {
        cartService.removeVoucher();
        return "redirect:/cart";
    }

    // --------------------------------

    // 6. Xem form thanh toán
    @GetMapping("/checkout")
    public String checkout(Model model) {
        // Logic bảo vệ: Giỏ hàng trống thì không cho thanh toán
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart?empty";
        }

        model.addAttribute("order", new Order());
        model.addAttribute("total", cartService.getTotal()); // Tổng tiền đã trừ voucher
        return "checkout";
    }

    // 7. Xử lý khi bấm nút "Xác Nhận Đặt Hàng"
    @PostMapping("/checkout")
    public String confirmCheckout(@ModelAttribute Order order) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart?empty";
        }

        order.setCreateAt(new Date());
        order.setTotalPrice(cartService.getTotal()); // Lưu tổng tiền thực tế phải trả

        if ("COD".equals(order.getPaymentMethod())) {
            order.setStatus("Mới đặt - Chờ thanh toán");
        } else {
            order.setStatus("Đã thanh toán qua " + order.getPaymentMethod());
        }

        orderRepository.save(order);
        cartService.clear(); // Xóa giỏ hàng (Voucher cũng tự mất theo)

        return "redirect:/?success";
    }
}