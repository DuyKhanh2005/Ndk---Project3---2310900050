package com.devmaster.lesson01_spring_boot.lambda_expression;

// Functional Interface có nhiều tham số
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class LambdaExpression3 {
    public static void main(String[] args) {

        // Lambda có 2 tham số
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> {
            if (b == 0) {
                System.out.println("Không thể chia cho 0!");
                return 0;
            }
            return a / b;
        };

        int x = 10, y = 5;

        // Gọi các biểu thức Lambda
        System.out.println("Cộng: " + add.calculate(x, y));
        System.out.println("Trừ: " + subtract.calculate(x, y));
        System.out.println("Nhân: " + multiply.calculate(x, y));
        System.out.println("Chia: " + divide.calculate(x, y));
    }
}
