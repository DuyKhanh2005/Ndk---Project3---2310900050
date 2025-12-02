package com.example.project3.controller;
import com.example.project3.entity.*;
import com.example.project3.repository.*;
import com.example.project3.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller @RequestMapping("/cart")
public class CartController {
    @Autowired private CartService cartService;
    @Autowired private OrderRepository orderRepo;
    @Autowired private OrderDetailRepository detailRepo;

    @GetMapping("") public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }
    @GetMapping("/add/{id}") public String add(@PathVariable Long id) { cartService.addToCart(id); return "redirect:/cart"; }
    @GetMapping("/remove/{id}") public String remove(@PathVariable Long id) { cartService.remove(id); return "redirect:/cart"; }
    @PostMapping("/apply-voucher") public String apply(@RequestParam String code) { cartService.applyVoucher(code); return "redirect:/cart"; }
    @GetMapping("/remove-voucher") public String removeVoucher() { cartService.removeVoucher(); return "redirect:/cart"; }

    @GetMapping("/checkout") public String checkout(Model model) {
        if (cartService.getItems().isEmpty()) return "redirect:/cart?empty";
        model.addAttribute("order", new Order());
        model.addAttribute("total", cartService.getTotal());
        return "checkout";
    }

    @PostMapping("/checkout") public String confirm(@ModelAttribute Order order) {
        if (cartService.getItems().isEmpty()) return "redirect:/cart?empty";
        order.setCreateAt(new Date());
        order.setTotalPrice(cartService.getTotal());
        order.setStatus("COD".equals(order.getPaymentMethod()) ? "Mới đặt" : "Đã thanh toán " + order.getPaymentMethod());
        Order saved = orderRepo.save(order);

        for (Map<String, Object> item : cartService.getItems()) {
            OrderDetail d = new OrderDetail();
            d.setOrder(saved);
            d.setProduct((Product) item.get("product"));
            d.setQuantity((Integer) item.get("quantity"));
            d.setPrice(((Product) item.get("product")).getPrice());
            detailRepo.save(d);
        }
        cartService.clear();
        return "redirect:/cart/order/" + saved.getId();
    }

    @GetMapping("/order/{id}") public String success(@PathVariable Long id, Model model) {
        Order o = orderRepo.findById(id).orElse(null);
        if (o == null) return "redirect:/";
        model.addAttribute("order", o);
        model.addAttribute("details", o.getOrderDetails());
        return "order_details";
    }
}