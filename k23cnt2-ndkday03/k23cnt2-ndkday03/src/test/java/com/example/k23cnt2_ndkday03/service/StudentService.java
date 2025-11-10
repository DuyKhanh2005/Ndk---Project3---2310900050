package com.example.k23cnt2_ndkday03.service;

import com.example.k23cnt2_ndkday03.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    List<StudentDTO> getAll();
    StudentDTO getById(Long id);
    StudentDTO save(StudentDTO dto);
    StudentDTO update(Long id, StudentDTO dto);
    void delete(Long id);
}