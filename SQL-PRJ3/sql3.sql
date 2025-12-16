-- 1. XÓA SẠCH VÀ TẠO LẠI DATABASE
DROP DATABASE IF EXISTS project3_db;
CREATE DATABASE project3_db;
USE project3_db;

-- 2. TẠO BẢNG USERS (Phone, Address)
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, 
    email VARCHAR(255),
    full_name VARCHAR(255),
    phone VARCHAR(20),
    address VARCHAR(255),
    role VARCHAR(50) DEFAULT 'USER',
    active BIT(1) DEFAULT 1
);

-- 3. TẠO BẢNG PRODUCTS (Description)
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE,
    sale_price DOUBLE,
    image VARCHAR(255),
    category VARCHAR(255),
    hot BIT(1),
    description TEXT -- Cột mới thêm để chứa mô tả dài
);

-- 4. TẠO BẢNG ORDERS (Đơn hàng)
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    customer_phone VARCHAR(20),
    customer_address VARCHAR(255),
    total_price DOUBLE,
    status VARCHAR(50),
    payment_method VARCHAR(50), -- Phương thức thanh toán (COD, QR...)
    create_at DATETIME,
    user_id BIGINT -- Liên kết với Users (để xem lịch sử)
);

-- 5. TẠO BẢNG ORDER_DETAILS (Chi tiết)
CREATE TABLE order_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT,
    price DOUBLE,
    order_id BIGINT,
    product_id BIGINT
);

-- 6. TẠO BẢNG VOUCHERS 
CREATE TABLE vouchers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) UNIQUE,
    discount_amount DOUBLE,
    expiry_date DATE
);

-- 7. TẠO BẢNG EMPLOYEES 
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    department VARCHAR(50),
    position VARCHAR(50),
    salary DOUBLE,
    hire_date DATE
);

-- 8. DỮ LIỆU TÀI KHOẢN MẪU 
INSERT INTO users (username, password, email, full_name, role, active)
VALUES ('admin', '123456', 'admin@techstore.com', 'Administrator', 'ADMIN', 1);

INSERT INTO users (username, password, email, full_name, role, active)
VALUES ('khachhang', '123456', 'khach@gmail.com', 'Nguyễn Văn Khách', 'USER', 1);

