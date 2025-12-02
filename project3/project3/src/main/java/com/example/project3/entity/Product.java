package com.example.project3.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data @Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Double salePrice;
    private String image;
    private String category;
    private Boolean hot = false;
}