package com.example.project3.repository;

import com.example.project3.entity.Order; // Đã thêm IMPORT
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> { }