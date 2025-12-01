package com.example.project3.service;

import com.example.project3.entity.Product;
import com.example.project3.entity.Voucher; // Import Voucher
import com.example.project3.repository.ProductRepository;
import com.example.project3.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.time.LocalDate;
import java.util.*;

@Service
@SessionScope
public class CartService {
    private List<Map<String, Object>> items = new ArrayList<>();
    private Voucher currentVoucher = null; // Lưu voucher hiện tại đang dùng

    @Autowired private ProductRepository productRepo;
    @Autowired private VoucherRepository voucherRepo;

    // --- Các hàm cũ (addToCart, remove...) GIỮ NGUYÊN ---
    public void addToCart(Long productId) { /* ... code cũ ... */
        var p = productRepo.findById(productId).orElse(null);
        if (p == null) return;
        for (Map<String, Object> item : items) {
            if (((Product) item.get("product")).getId().equals(productId)) {
                int qty = (int) item.get("quantity");
                item.put("quantity", qty + 1); return;
            }
        }
        Map<String, Object> newItem = new HashMap<>();
        newItem.put("product", p); newItem.put("quantity", 1);
        items.add(newItem);
    }

    public void remove(Long productId) { items.removeIf(item -> ((Product) item.get("product")).getId().equals(productId)); }
    public List<Map<String, Object>> getItems() { return items; }

    // --- CÁC HÀM MỚI VỀ VOUCHER ---

    public void clear() {
        items.clear();
        currentVoucher = null; // Xóa giỏ là mất voucher
    }

    // Hàm áp dụng Voucher
    public String applyVoucher(String code) {
        Voucher v = voucherRepo.findByCode(code);
        if (v == null) return "Mã giảm giá không tồn tại!";
        if (v.getExpiryDate().isBefore(LocalDate.now())) return "Mã giảm giá đã hết hạn!";

        this.currentVoucher = v;
        return "Áp dụng thành công!";
    }

    public void removeVoucher() {
        this.currentVoucher = null;
    }

    public Voucher getVoucher() {
        return currentVoucher;
    }

    public double getTotal() {
        double subtotal = items.stream().mapToDouble(i -> ((Product)i.get("product")).getPrice() * (int)i.get("quantity")).sum();

        // Trừ tiền giảm giá
        if (currentVoucher != null) {
            subtotal -= currentVoucher.getDiscountAmount();
        }

        return subtotal > 0 ? subtotal : 0; // Không được âm tiền
    }
}