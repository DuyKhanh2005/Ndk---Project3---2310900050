package com.devmaster.lesson01_spring_boot.lambda_expression;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortLambdaExample {
    public static void main(String[] args) {
        // Tạo danh sách các chuỗi
        List<String> list = Arrays.asList("Java SpringBoot", "C# NetCore", "PHP", "JavaScript");

        System.out.println("Trước khi sắp xếp:");
        list.forEach(System.out::println);

        // Sắp xếp danh sách bằng biểu thức Lambda
        Collections.sort(list, (str1, str2) -> str1.compareTo(str2));

        System.out.println("\nSau khi sắp xếp (tăng dần):");
        list.forEach(System.out::println);

        // Sắp xếp giảm dần để thấy rõ hơn
        Collections.sort(list, (a, b) -> b.compareTo(a));

        System.out.println("\nSau khi sắp xếp (giảm dần):");
        list.forEach(System.out::println);
    }
}
