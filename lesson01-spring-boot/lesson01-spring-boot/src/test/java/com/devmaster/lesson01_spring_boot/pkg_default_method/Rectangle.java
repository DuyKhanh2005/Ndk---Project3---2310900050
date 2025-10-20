package com.devmaster.lesson01_spring_boot.pkg_default_method;

// Lớp triển khai interface Shape - đại diện cho hình chữ nhật
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Vẽ hình chữ nhật");
    }
}
