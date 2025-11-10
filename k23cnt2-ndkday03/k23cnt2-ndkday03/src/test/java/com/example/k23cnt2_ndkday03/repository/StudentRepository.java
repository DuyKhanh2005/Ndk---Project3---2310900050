package com.example.k23cnt2_ndkday03.repository;

import com.example.k23cnt2_ndkday03.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}