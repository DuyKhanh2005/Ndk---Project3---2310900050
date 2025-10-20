package com.devmaster.lesson01_spring_boot.pkg_default_method;

// Lớp triển khai interface Shape - đại diện cho hình tam giác
public class Triangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Vẽ hình tam giác");
    }
}
