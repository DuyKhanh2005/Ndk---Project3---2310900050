package com.example.project3.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity @Data @Table(name = "vouchers")
public class Voucher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) private String code;
    private Double discountAmount;
    private LocalDate expiryDate;
}