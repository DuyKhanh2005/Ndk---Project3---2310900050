package com.example.project3.service;

import com.example.project3.entity.Product;
import com.example.project3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Đường dẫn lưu ảnh (bạn có thể đổi thành đường dẫn tuyệt đối nếu cần)
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

    public void saveProduct(Product product, MultipartFile file) throws IOException {
        // 1. Xử lý upload ảnh (nếu có chọn ảnh)
        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            // Tạo tên file ngẫu nhiên để tránh trùng
            String newFileName = UUID.randomUUID().toString() + "_" + originalFilename;

            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, newFileName);
            Files.write(fileNameAndPath, file.getBytes());

            // Lưu tên ảnh vào database
            product.setImage(newFileName);
        }

        // 2. Lưu sản phẩm vào DB
        productRepository.save(product);
    }
}