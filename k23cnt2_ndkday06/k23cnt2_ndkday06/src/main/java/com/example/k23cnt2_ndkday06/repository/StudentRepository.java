package com.example.k23cnt2_ndkday06.repository;

import com.example.k23cnt2_ndkday06.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}