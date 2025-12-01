package com.example.project3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString; // Thêm cái này
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private Double totalPrice;
    private String status;
    private String paymentMethod; // ZaloPay, COD...

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt = new Date();

    // Mối quan hệ 1-Nhiều với OrderDetail
    // mappedBy = "order" nghĩa là nó tìm biến tên là "order" trong file OrderDetail.java
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @ToString.Exclude // Ngắt vòng lặp vô hạn khi in log
    private List<OrderDetail> orderDetails;
}