package com.devmaster.lesson01_spring_boot.pkg_default_method;

// Interface Shape với phương thức trừu tượng và phương thức default
public interface Shape {

    // Phương thức trừu tượng: mỗi hình sẽ có cách vẽ riêng
    void draw();

    // Phương thức default: có sẵn nội dung, lớp con có thể dùng hoặc ghi đè
    default void setColor(String color) {
        System.out.println("Vẽ hình với màu " + color);
    }
}
