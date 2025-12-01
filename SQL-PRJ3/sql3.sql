-- 1. RESET DATABASE (Xóa cũ tạo mới cho sạch)
DROP DATABASE IF EXISTS project3_db;
CREATE DATABASE project3_db;
USE project3_db;

-- 2. TẠO BẢNG USERS (Đã thêm Phone và Address)
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, 
    email VARCHAR(255),
    full_name VARCHAR(255),
    phone VARCHAR(20),      -- Cột mới thêm
    address VARCHAR(255),   -- Cột mới thêm
    role VARCHAR(50) DEFAULT 'USER', -- ADMIN hoặc USER
    active BIT(1) DEFAULT 1
);

-- 3. TẠO BẢNG PRODUCTS (Sản phẩm)
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE,
    sale_price DOUBLE,
    image VARCHAR(255),
    category VARCHAR(255),
    hot BIT(1),
    description TEXT
);

-- 4. TẠO BẢNG ORDERS (Đơn hàng)
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    customer_phone VARCHAR(20),
    customer_address VARCHAR(255),
    total_price DOUBLE,
    status VARCHAR(50),
    create_at DATETIME
);

-- 5. TẠO BẢNG ORDER_DETAILS (Chi tiết đơn hàng)
CREATE TABLE order_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT,
    price DOUBLE,
    order_id BIGINT,
    product_id BIGINT
);

-- 6. CHÈN DỮ LIỆU MẪU (SEEDING)

-- Admin User (Thêm sđt và địa chỉ ảo)
INSERT INTO users (username, password, email, full_name, phone, address, role, active)
VALUES 
('admin', '123456', 'admin@techstore.com', 'Administrator', '0999888777', 'Hệ Thống', 'ADMIN', 1);

-- Khách hàng mẫu (Để test chức năng User)
INSERT INTO users (username, password, email, full_name, phone, address, role, active)
VALUES 
('khachhang', '123456', 'khach@gmail.com', 'Nguyễn Văn Khách', '0912345678', 'Hà Nội', 'USER', 1);

-- Sample Products (Sản phẩm mẫu)
INSERT INTO products (name, price, sale_price, image, category, hot) 
VALUES 
('iPhone 15 Pro Max 256GB', 34990000, 29590000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-15-pro-max_3.jpg', 'Apple', 1),
('Samsung Galaxy S24 Ultra', 33990000, 26990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/s/ss-s24-ultra-xam-222.jpg', 'Samsung', 1),
('MacBook Air M2 2022', 28990000, 24490000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/macbook-air-m2-midnight-1.jpg', 'Laptop', 0),
('Xiaomi Redmi Note 13', 7500000, 5990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/x/i/xiaomi-redmi-note-13-pro-4g-1.png', 'Xiaomi', 0);

TRUNCATE TABLE products;