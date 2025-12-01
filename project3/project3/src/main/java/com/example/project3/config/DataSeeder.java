package com.example.project3.config;

import com.example.project3.entity.Product;
import com.example.project3.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner initDatabase(ProductRepository repo) {
        return args -> {
            // Chỉ tạo dữ liệu nếu bảng đang trống
            if (repo.count() == 0) {
                List<Product> list = List.of(

                        // 1. Apple
                        create("iPhone 15 Pro Max Titanium", 34990000.0, 29500000.0, "Apple",
                                "/images/iphone15.png", true),

                        // 2. Samsung
                        create("Samsung Galaxy S25 Ultra", 33990000.0, 26990000.0, "Samsung",
                                "/images/samsungS25ultra.png", true),

                        // 3. Laptop Gaming
                        create("Asus ROG Strix Gaming", 32000000.0, 29990000.0, "Laptop",
                                "/images/asusROG.png", true),

                        // 4. Laptop Văn phòng
                        create("MacBook Air M2 Midnight", 28990000.0, 24500000.0, "Laptop",
                                "/images/macbook.png", false),

                        // 5. Tablet
                        create("iPad Pro 12.9 inch M2", 28000000.0, 26990000.0, "Apple",
                                "/images/ipad.png", false),

                        // 6. Phụ kiện
                        create("Tai nghe Sony WH-1000XM5", 8990000.0, 6990000.0, "Phụ kiện",
                                "/images/tainghe.png", false)
                );
                repo.saveAll(list);
            }
        };
    }

    private Product create(String name, Double price, Double salePrice, String cate, String img, Boolean hot) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setSalePrice(salePrice);
        p.setCategory(cate);
        p.setImage(img);
        p.setHot(hot);
        return p;
    }
}