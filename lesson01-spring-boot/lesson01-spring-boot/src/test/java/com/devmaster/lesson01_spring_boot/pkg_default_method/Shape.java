package com.devmaster.lesson01_spring_boot.pkg_default_method;

public interface Shape {
    void draw();

    // Thêm hỗ trợ màu bằng mã ANSI
    default void setColor(String color) {
        String colorCode;

        switch (color.toLowerCase()) {
            case "đỏ":
                colorCode = "\u001B[31m"; // Red
                break;
            case "xanh":
                colorCode = "\u001B[34m"; // Blue
                break;
            case "vàng":
                colorCode = "\u001B[33m"; // Yellow
                break;
            case "lục":
            case "xanh lá":
                colorCode = "\u001B[32m"; // Green
                break;
            default:
                colorCode = "\u001B[0m"; // Reset
        }

        // In ra chữ màu + reset về bình thường
        System.out.println(colorCode + "Vẽ hình với màu: " + color + "\u001B[0m");
    }
}
