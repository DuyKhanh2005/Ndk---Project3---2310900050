package com.devmaster.lesson01_spring_boot.pkg_default_method;

// Lớp triển khai interface Shape - đại diện cho hình tròn
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Vẽ hình tròn");
    }
}
