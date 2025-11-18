package com.example.k23cnt2_ndkday06.dto;

import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private int age;
}
