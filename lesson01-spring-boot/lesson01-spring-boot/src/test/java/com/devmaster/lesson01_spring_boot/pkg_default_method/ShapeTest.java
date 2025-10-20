package com.devmaster.lesson01_spring_boot.pkg_default_method;

// Lớp chính để kiểm tra các lớp hình
public class ShapeTest {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();
        Shape triangle = new Triangle();

        circle.setColor("Đỏ");
        circle.draw();

        rectangle.setColor("Xanh");
        rectangle.draw();

        triangle.setColor("Vàng");
        triangle.draw();
    }
}
