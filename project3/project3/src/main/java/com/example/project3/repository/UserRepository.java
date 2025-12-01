package com.example.project3.repository;

import com.example.project3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // BỔ SUNG: Method này giúp Spring Security tìm kiếm User theo username
    User findByUsername(String username);
}