package com.example.project3.repository;

import com.example.project3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. Tìm kiếm theo tên (Dùng cho ô Search)
    List<Product> findByNameContainingIgnoreCase(String name);

    // 2. Tìm kiếm theo danh mục (Dùng cho thanh Filter Bar) --> BẠN ĐANG THIẾU DÒNG NÀY
    List<Product> findByCategoryIgnoreCase(String category);
}