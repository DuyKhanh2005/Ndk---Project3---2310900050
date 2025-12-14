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
                                "/images/iphone15.png", true,
                                "Khung viền Titan bền bỉ, chip A17 Pro mạnh mẽ nhất lịch sử Apple. Camera 48MP zoom quang 5x đỉnh cao. Cổng sạc USB-C tốc độ cao."),

                        // 2. Samsung
                        create("Samsung Galaxy S25 Ultra", 33990000.0, 26990000.0, "Samsung",
                                "/images/samsungS25ultra.png", true,
                                "Quyền năng Galaxy AI tích hợp. Chip Snapdragon 8 Gen 4 for Galaxy. Khung viền Titan, Camera 200MP Mắt Thần Bóng Đêm. Bút S-Pen quyền năng."),

                        // 3. Laptop Gaming
                        create("Asus ROG Strix Gaming", 32000000.0, 29990000.0, "Laptop",
                                "/images/asusROG.png", true,
                                "Chiến binh Gaming thực thụ. Card đồ họa RTX 4060, màn hình 240Hz siêu mượt. Hệ thống tản nhiệt 3 quạt mát lạnh. Đèn LED RGB Aura Sync."),

                        // 4. Laptop Văn phòng
                        create("MacBook Air M2 Midnight", 28990000.0, 24500000.0, "Laptop",
                                "/images/macbook.png", false,
                                "Thiết kế siêu mỏng nhẹ, chip M2 hiệu năng đột phá. Thời lượng pin lên đến 18 giờ. Màn hình Liquid Retina tuyệt đẹp."),

                        // 5. Tablet
                        create("iPad Pro 12.9 inch M2", 28000000.0, 26990000.0, "Apple",
                                "/images/ipad.png", false,
                                "Trải nghiệm iPad tối thượng với chip M2. Màn hình Mini-LED XDR rực rỡ. Hỗ trợ Apple Pencil 2 lướt chạm cực nhạy."),

                        // 6. Phụ kiện - Tai nghe
                        create("Tai nghe Sony WH-1000XM5", 8990000.0, 6990000.0, "Phụ kiện",
                                "/images/tainghe.png", false,
                                "Vua chống ồn chủ động. Thiết kế mới nhẹ hơn, ôm tai hơn. Chất âm Hi-Res Audio Wireless đỉnh cao. Pin trâu 30 giờ."),

                        // --- 4 SẢN PHẨM MỚI THÊM ---

                        // 7. Phụ kiện - Bàn phím
                        create("Bàn phím cơ Keychron K8 Pro", 3200000.0, 2890000.0, "Phụ kiện",
                                "/images/keyboard.png", true,
                                "Bàn phím cơ không dây custom cao cấp. Hỗ trợ QMK/VIA, switch Gateron G Pro mượt mà. Khung nhôm chắc chắn, kết nối đa thiết bị."),

                        // 8. Phụ kiện - Chuột
                        create("Chuột Logitech MX Master 3S", 2490000.0, 1990000.0, "Phụ kiện",
                                "/images/mouse.png", false,
                                "Chuột văn phòng tốt nhất thế giới. Cảm biến 8000 DPI, cuộn từ trường MagSpeed siêu nhanh. Click chuột yên tĩnh giảm 90% tiếng ồn."),

                        // 9. Đồng hồ thông minh
                        create("Apple Watch Series 9 GPS", 10990000.0, 9590000.0, "Apple",
                                "/images/watch.png", true,
                                "Chip S9 SiP mạnh mẽ, màn hình sáng gấp đôi. Tính năng chạm hai lần (Double Tap) độc đáo. Theo dõi sức khỏe tim mạch chuyên sâu."),

                        // 10. Loa Bluetooth
                        create("Loa JBL Charge 5", 3990000.0, 3290000.0, "Phụ kiện",
                                "/images/speaker.png", false,
                                "Âm thanh JBL Original Pro Sound mạnh mẽ. Chống nước bụi chuẩn IP67. Pin 20 giờ chơi nhạc liên tục, tích hợp sạc dự phòng.")
                );
                repo.saveAll(list);
            }
        };
    }

    private Product create(String name, Double price, Double salePrice, String cate, String img, Boolean hot, String description) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setSalePrice(salePrice);
        p.setCategory(cate);
        p.setImage(img);
        p.setHot(hot);
        p.setDescription(description);
        return p;
    }
}