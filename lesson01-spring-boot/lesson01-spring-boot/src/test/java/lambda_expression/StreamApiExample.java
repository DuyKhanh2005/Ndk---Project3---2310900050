package com.devmaster.lesson01_spring_boot.lambda_expression;

import java.util.*;
import java.util.stream.*;

public class StreamApiExample {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Khánh", "An", "Minh", "Linh", "Hà", "Khánh");

        // 1️⃣ Lọc (filter): chỉ lấy tên dài hơn 3 ký tự
        System.out.println("Tên dài hơn 3 ký tự:");
        names.stream()
                .filter(n -> n.length() > 3)
                .forEach(System.out::println);

        // 2️⃣ Chuyển đổi (map): viết hoa tất cả tên
        System.out.println("\nViết hoa tất cả tên:");
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // 3️⃣ Loại bỏ trùng lặp (distinct)
        System.out.println("\nLoại bỏ trùng lặp:");
        names.stream()
                .distinct()
                .forEach(System.out::println);

        // 4️⃣ Sắp xếp (sorted)
        System.out.println("\nSắp xếp theo ABC:");
        names.stream()
                .sorted()
                .forEach(System.out::println);

        // 5️⃣ Đếm (count)
        long count = names.stream()
                .filter(n -> n.startsWith("K"))
                .count();
        System.out.println("\nCó " + count + " tên bắt đầu bằng K");

        // 6️⃣ Thu kết quả (collect)
        System.out.println("\nTạo danh sách tên viết hoa:");
        List<String> upperList = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        upperList.forEach(System.out::println);
    }
}
