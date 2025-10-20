package com.devmaster.lesson01_spring_boot.pkg_default_method;

public interface Shape {

    // Phương thức trừu tượng: mỗi hình sẽ tự cài đặt cách vẽ
    void draw();

    // Phương thức default: có sẵn nội dung, lớp con có thể dùng hoặc ghi đè
    default void setColor(String color) {
        System.out.println("Vẽ hình với màu " + color);
    }
}
