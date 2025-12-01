package com.example.k23cnt2_ndkday07.repository;

import com.example.k23cnt2_ndkday07.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Tìm sách theo code (unique)
    Book findByCode(String code);

    // Kiểm tra code đã tồn tại chưa
    boolean existsByCode(String code);
}