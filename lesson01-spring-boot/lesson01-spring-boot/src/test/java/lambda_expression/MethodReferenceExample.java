package com.devmaster.lesson01_spring_boot.lambda_expression;

import java.util.*;
import java.util.function.Supplier;

public class MethodReferenceExample {

    public static void main(String[] args) {

        // 1️⃣ Tham chiếu phương thức tĩnh
        List<Integer> numbers = Arrays.asList(5, 10, 15, 20);
        numbers.forEach(MethodReferenceExample::printNumber);

        // 2️⃣ Tham chiếu phương thức của đối tượng
        System.out.println("\n--- In bằng System.out::println ---");
        numbers.forEach(System.out::println);

        // 3️⃣ Tham chiếu phương thức instance của lớp
        System.out.println("\n--- Chuyển tên sang viết hoa ---");
        List<String> names = Arrays.asList("Khánh", "Linh", "Minh");
        names.stream().map(String::toUpperCase).forEach(System.out::println);

        // 4️⃣ Tham chiếu constructor (tạo đối tượng)
        System.out.println("\n--- Tạo đối tượng mới bằng constructor reference ---");
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        newList.add("DevMaster");
        newList.add("Java");
        newList.forEach(System.out::println);
    }

    public static void printNumber(int n) {
        System.out.println("Số: " + n);
    }
}
