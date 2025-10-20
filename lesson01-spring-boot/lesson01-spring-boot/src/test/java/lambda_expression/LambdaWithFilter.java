package com.devmaster.lesson01_spring_boot.lambda_expression;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaWithFilter {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(10, 3, 15, 8, 21, 5, 7, 30, 18);

        System.out.println("Danh sách ban đầu:");
        numbers.forEach(n -> System.out.print(n + " "));

        System.out.println("\n\nSố chẵn:");
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

        System.out.println("\nSố > 10:");
        numbers.stream().filter(n -> n > 10).forEach(System.out::println);

        System.out.println("\nSố chẵn và > 10:");
        numbers.stream().filter(n -> n > 10 && n % 2 == 0).forEach(System.out::println);

        List<Integer> result = numbers.stream().filter(n -> n < 15).collect(Collectors.toList());
        System.out.println("\nSố < 15: " + result);

        List<String> names = Arrays.asList("Khánh", "Minh", "Lan", "An", "Huy", "Linh");

        System.out.println("\nTên bắt đầu bằng L:");
        names.stream().filter(n -> n.startsWith("L")).forEach(System.out::println);

        System.out.println("\nTên có độ dài > 3:");
        names.stream().filter(n -> n.length() > 3).forEach(System.out::println);

        System.out.println("\nTên viết hoa có độ dài > 3:");
        names.stream().map(String::toUpperCase).filter(n -> n.length() > 3).forEach(System.out::println);
    }
}
