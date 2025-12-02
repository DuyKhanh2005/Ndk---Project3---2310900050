package com.example.project3.controller;

import com.example.project3.entity.Order;
import com.example.project3.entity.OrderDetail;
import com.example.project3.entity.Product;
import com.example.project3.entity.User;
import com.example.project3.repository.OrderDetailRepository;
import com.example.project3.repository.OrderRepository;
import com.example.project3.repository.UserRepository;
import com.example.project3.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private CartService cartService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderDetailRepository orderDetailRepository;
    @Autowired private UserRepository userRepository;

    // 1. Xem giỏ hàng
    @GetMapping("")
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    // 2. Thêm vào giỏ (HÀM NÀY QUAN TRỌNG ĐỂ NÚT MUA HOẠT ĐỘNG)
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

    // 4. Áp dụng Voucher
    @PostMapping("/apply-voucher")
    public String applyVoucher(@RequestParam("code") String code) {
        cartService.applyVoucher(code);
        return "redirect:/cart";
    }

    // 5. Hủy Voucher
    @GetMapping("/remove-voucher")
    public String removeVoucher() {
        cartService.removeVoucher();
        return "redirect:/cart";
    }

    // 6. Trang thanh toán
    @GetMapping("/checkout")
    public String checkout(Model model) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart?empty";
        }

        // Tạo đơn hàng mới để điền form
        Order order = new Order();

        // Tự động điền thông tin người dùng nếu đã đăng nhập
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            User currentUser = userRepository.findByUsername(auth.getName());
            if (currentUser != null) {
                order.setCustomerName(currentUser.getFullName());
                order.setCustomerPhone(currentUser.getPhone());
                order.setCustomerAddress(currentUser.getAddress());
            }
        }

        model.addAttribute("order", order);
        model.addAttribute("total", cartService.getTotal());
        return "checkout";
    }

    // 7. Xử lý Đặt hàng (Lưu User, Order, OrderDetail)
    @PostMapping("/checkout")
    public String confirmCheckout(@ModelAttribute Order order) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart?empty";
        }

        // A. Gán User cho đơn hàng (Nếu đã đăng nhập)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            User currentUser = userRepository.findByUsername(auth.getName());
            if (currentUser != null) {
                order.setUser(currentUser); // Quan trọng để xem lại lịch sử
            }
        }

        // B. Thiết lập thông tin đơn hàng
        order.setCreateAt(new Date());
        order.setTotalPrice(cartService.getTotal());

        if ("COD".equals(order.getPaymentMethod())) {
            order.setStatus("Mới đặt - Chờ thanh toán");
        } else {
            order.setStatus("Đã thanh toán qua " + order.getPaymentMethod());
        }

        // C. Lưu Đơn hàng chính
        Order savedOrder = orderRepository.save(order);

        // D. Lưu Chi tiết đơn hàng
        for (Map<String, Object> item : cartService.getItems()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);
            detail.setProduct((Product) item.get("product"));
            detail.setQuantity((Integer) item.get("quantity"));
            detail.setPrice(((Product) item.get("product")).getPrice());

            orderDetailRepository.save(detail);
        }

        // E. Xóa giỏ hàng và chuyển hướng
        cartService.clear();
        return "redirect:/cart/order/" + savedOrder.getId();
    }

    // 8. Trang xem chi tiết đơn hàng thành công
    @GetMapping("/order/{id}")
    public String orderSuccess(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) return "redirect:/";

        model.addAttribute("order", order);
        model.addAttribute("details", order.getOrderDetails());
        return "order_details";
    }
}