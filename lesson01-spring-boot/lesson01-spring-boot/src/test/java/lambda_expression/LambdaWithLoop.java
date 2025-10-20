package com.devmaster.lesson01_spring_boot.lambda_expression;

import java.util.*;  // Để dùng List, Map
import java.util.stream.Collectors; // Dùng cho phần nâng cao

public class LambdaWithLoop {
    public static void main(String[] args) {


        // 1️⃣ Duyệt List bằng forEach (Lambda)
        List<String> names = Arrays.asList("Khánh", "Minh", "Lan", "Huy", "An");

        System.out.println("Duyệt danh sách tên (forEach với Lambda):");
        names.forEach(name -> System.out.println("Xin chào " + name));


        // 2️⃣ Duyệt Map bằng Lambda
        Map<Integer, String> students = new HashMap<>();
        students.put(1, "Khánh");
        students.put(2, "Minh");
        students.put(3, "Lan");

        System.out.println("\nDuyệt Map (key, value) với Lambda:");
        students.forEach((id, name) -> System.out.println("ID: " + id + " - Tên: " + name));


        // 3️⃣ Duyệt List có điều kiện (Lambda + if)
        System.out.println("\nDuyệt danh sách, chỉ in tên dài hơn 3 ký tự:");
        names.forEach(n -> {
            if (n.length() > 3)
                System.out.println("Tên: " + n);
        });


        // 4️⃣ Kết hợp Lambda + Stream API
        System.out.println("\nIn các tên bắt đầu bằng chữ 'L':");
        names.stream()
                .filter(n -> n.startsWith("L")) // lọc tên bắt đầu bằng L
                .forEach(System.out::println); // in ra

        // 5️⃣ Chuyển đổi phần tử (map)
        System.out.println("\nTên viết hoa (Lambda map):");
        List<String> upperNames = names.stream()
                .map(n -> n.toUpperCase())
                .collect(Collectors.toList());
        upperNames.forEach(System.out::println);


        // 6️⃣ Duyệt ngược bằng forEachOrdered
        System.out.println("\nDuyệt có thứ tự (forEachOrdered):");
        names.stream()
                .sorted() // sắp xếp tăng dần
                .forEachOrdered(System.out::println);
    }
}
