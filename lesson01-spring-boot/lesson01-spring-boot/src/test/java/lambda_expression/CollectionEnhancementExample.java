package com.devmaster.lesson01_spring_boot.lambda_expression;

import java.util.*;

public class CollectionEnhancementExample {
    public static void main(String[] args) {

        // 1️⃣ forEach() - Duyệt danh sách
        List<String> list = new ArrayList<>(Arrays.asList("Khánh", "Linh", "An", "Minh"));
        System.out.println("Duyệt danh sách:");
        list.forEach(System.out::println);

        // 2️⃣ removeIf() - Xóa phần tử có độ dài <= 3
        list.removeIf(name -> name.length() <= 3);
        System.out.println("\nSau khi removeIf:");
        list.forEach(System.out::println);

        // 3️⃣ replaceAll() - Ghi đè tất cả phần tử bằng chữ hoa
        list.replaceAll(String::toUpperCase);
        System.out.println("\nSau khi replaceAll:");
        list.forEach(System.out::println);

        // 4️⃣ sort() - Sắp xếp theo thứ tự giảm dần
        list.sort((a, b) -> b.compareTo(a));
        System.out.println("\nSau khi sort giảm dần:");
        list.forEach(System.out::println);

        // 5️⃣ Map enhancements
        System.out.println("\n--- Map Enhancements ---");
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "Spring");
        map.put(3, "Boot");

        // forEach() cho Map
        map.forEach((k, v) -> System.out.println("Key=" + k + ", Value=" + v));

        // getOrDefault()
        System.out.println("\ngetOrDefault(4, 'Unknown'): " + map.getOrDefault(4, "Unknown"));

        // putIfAbsent()
        map.putIfAbsent(3, "API");  // không thay đổi vì key 3 đã có
        map.putIfAbsent(4, "JPA");  // thêm mới
        System.out.println("\nSau putIfAbsent:");
        map.forEach((k, v) -> System.out.println(k + " => " + v));

        // computeIfPresent()
        map.computeIfPresent(2, (k, v) -> v + " Framework");
        System.out.println("\nSau computeIfPresent:");
        map.forEach((k, v) -> System.out.println(k + " => " + v));
    }
}
