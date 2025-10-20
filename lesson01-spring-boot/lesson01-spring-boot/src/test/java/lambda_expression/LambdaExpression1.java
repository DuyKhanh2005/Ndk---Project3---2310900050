package com.devmaster.lesson01_spring_boot.lambda_expression;

// Đánh dấu interface chỉ có 1 phương thức trừu tượng
@FunctionalInterface
interface SayHello1 {
    void sayHello(); // phương thức trừu tượng
}

public class LambdaExpression1 {
    public static void main(String[] args) {

        // Biểu thức Lambda không có tham số
        SayHello1 sayHello = () -> {
            System.out.println("Hello World");
        };

        // Gọi phương thức của interface
        sayHello.sayHello();
    }
}
