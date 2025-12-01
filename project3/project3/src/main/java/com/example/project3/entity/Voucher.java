package com.example.project3.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity @Data @Table(name = "vouchers")
public class Voucher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code; // Mã code (ví dụ: SALE50)

    private Double discountAmount; // Số tiền giảm (ví dụ: 50000)

    private LocalDate expiryDate; // Ngày hết hạn
}