package com.example.project3.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data @Table(name = "order_details")
public class OrderDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;

    @ManyToOne @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;
}