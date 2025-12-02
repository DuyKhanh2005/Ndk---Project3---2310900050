package com.example.project3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import java.util.List;

@Entity @Data @Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private Double totalPrice;
    private String status;
    private String paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt = new Date();

    // --- MỚI THÊM: Liên kết đơn hàng với User ---
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Để biết đơn này của ai
    // -------------------------------------------

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<OrderDetail> orderDetails;
}