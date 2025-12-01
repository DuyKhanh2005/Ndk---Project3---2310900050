package com.example.k23cnt2_ndkday07.service;

import com.example.k23cnt2_ndkday07.entity.Book;
import com.example.k23cnt2_ndkday07.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // Lấy tất cả sách
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // Lấy sách theo id
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    // Tìm theo code (nếu cần)
    public Book findByCode(String code) {
        return bookRepository.findByCode(code);
    }

    // Kiểm tra code đã tồn tại chưa
    public boolean existsByCode(String code) {
        return bookRepository.existsByCode(code);
    }

    // Lưu (thêm mới + cập nhật)
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Xóa theo id
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    // === Dành riêng cho Controller Thymeleaf (bắt buộc phải có) ===
    public List<Book> getAllBooks() {
        return findAll();
    }

    public Book getBookById(Long id) {
        return findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        deleteById(id);
    }
}