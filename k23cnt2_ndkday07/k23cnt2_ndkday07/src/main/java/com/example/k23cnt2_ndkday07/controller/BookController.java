package com.example.k23cnt2_ndkday07.controller;

import com.example.k23cnt2_ndkday07.entity.Author;
import com.example.k23cnt2_ndkday07.entity.Book;
import com.example.k23cnt2_ndkday07.service.AuthorService;
import com.example.k23cnt2_ndkday07.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired   // ← BẮT BUỘC PHẢI CÓ DÒNG NÀY!!!
    private BookService bookService;

    @Autowired   // ← VÀ DÒNG NÀY NỮA!!!
    private AuthorService authorService;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/products/";

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/book-list";
    }

    @GetMapping({"/new", "/edit/{id}"})
    public String form(@PathVariable(required = false) Long id, Model model) {
        Book book = id == null ? new Book() : bookService.findById(id).orElse(new Book());
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());  // ← DÙNG ĐƯỢC NHỜ @Autowired
        return "books/book-form";
    }

    @PostMapping("/save")
    public String save(Book book,
                       @RequestParam(name = "authorIds", required = false) List<Long> authorIds,
                       @RequestParam("imageBook") MultipartFile imageFile) {

        // Upload ảnh
        if (!imageFile.isEmpty()) {
            try {
                Files.createDirectories(Paths.get(UPLOAD_DIR));
                String ext = StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
                String fileName = book.getCode() + (ext != null ? "." + ext : "");
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.copy(imageFile.getInputStream(), path);
                book.setImgUrl("/images/products/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Gán tác giả
        book.getAuthors().clear();
        if (authorIds != null && !authorIds.isEmpty()) {
            List<Author> authors = authorService.findAllById(authorIds);  // ← DÙNG ĐƯỢC NHỜ @Autowired
            book.getAuthors().addAll(authors);
        }

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}