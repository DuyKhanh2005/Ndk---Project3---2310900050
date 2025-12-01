package com.example.project3.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity; // Số lượng mua
    private Double price;     // Giá tại thời điểm mua

    // --- QUAN TRỌNG: Liên kết ngược lại với Order ---
    // Biến này tên là "order" -> Khớp với mappedBy="order" bên file Order.java
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Liên kết với Product để biết là sản phẩm gì
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}