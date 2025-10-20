package com.devmaster.lesson01_spring_boot.lambda_expression;

// Interface chỉ có 1 phương thức trừu tượng
@FunctionalInterface
interface SayHello2 {
    void sayHello(String name);
}

public class LambdaExpression2 {
    public static void main(String[] args) {

        // Biểu thức Lambda có 1 tham số
        SayHello2 sayHello = (name) -> {
            System.out.println("Hello " + name);
        };

        // Gọi phương thức
        sayHello.sayHello("DevMaster");
        sayHello.sayHello("Khánh");

        // Viết ngắn gọn hơn (nếu chỉ 1 lệnh)
        SayHello2 shortForm = name -> System.out.println("Xin chào " + name);
        shortForm.sayHello("Java");
    }
}
