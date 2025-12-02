package com.example.project3.repository;

import com.example.project3.entity.Order;
import com.example.project3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByUserOrderByCreateAtDesc(User user);
}