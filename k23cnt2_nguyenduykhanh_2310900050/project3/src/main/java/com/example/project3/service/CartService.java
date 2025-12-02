package com.example.project3.service;
import com.example.project3.entity.Product;
import com.example.project3.entity.Voucher;
import com.example.project3.repository.ProductRepository;
import com.example.project3.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.time.LocalDate;
import java.util.*;

@Service @SessionScope
public class CartService {
    private List<Map<String, Object>> items = new ArrayList<>();
    private Voucher currentVoucher = null;
    @Autowired private ProductRepository productRepo;
    @Autowired private VoucherRepository voucherRepo;

    public void addToCart(Long productId) {
        var p = productRepo.findById(productId).orElse(null);
        if (p == null) return;
        for (Map<String, Object> item : items) {
            if (((Product) item.get("product")).getId().equals(productId)) {
                item.put("quantity", (int) item.get("quantity") + 1); return;
            }
        }
        Map<String, Object> newItem = new HashMap<>();
        newItem.put("product", p); newItem.put("quantity", 1);
        items.add(newItem);
    }
    public void remove(Long productId) { items.removeIf(item -> ((Product) item.get("product")).getId().equals(productId)); }
    public List<Map<String, Object>> getItems() { return items; }
    public void clear() { items.clear(); currentVoucher = null; }

    public void applyVoucher(String code) {
        Voucher v = voucherRepo.findByCode(code);
        if (v != null && !v.getExpiryDate().isBefore(LocalDate.now())) this.currentVoucher = v;
    }
    public void removeVoucher() { this.currentVoucher = null; }
    public Voucher getVoucher() { return currentVoucher; }

    public double getTotal() {
        double subtotal = items.stream().mapToDouble(i -> ((Product)i.get("product")).getPrice() * (int)i.get("quantity")).sum();
        if (currentVoucher != null) subtotal -= currentVoucher.getDiscountAmount();
        return subtotal > 0 ? subtotal : 0;
    }
}