package com.example.k23cnt2_ndkday07.service;

import com.example.k23cnt2_ndkday07.entity.Author;
import com.example.k23cnt2_ndkday07.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // Lấy tất cả tác giả
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    // Lấy theo id (dùng Optional)
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    // Lấy theo code
    public Author findByCode(String code) {
        return authorRepository.findByCode(code);
    }

    // Kiểm tra code đã tồn tại chưa
    public boolean existsByCode(String code) {
        return authorRepository.existsByCode(code);
    }

    // Lưu (thêm + sửa)
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    // Xóa
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    // ===== 3 method BẮT BUỘC phải có để Controller chạy được =====

    // Dùng trong form chọn nhiều tác giả
    public List<Author> findAllById(List<Long> ids) {
        return authorRepository.findAllById(ids);
    }

    // Dùng trong model.addAttribute("authors", ...)
    public List<Author> getAllAuthors() {
        return findAll();
    }
}