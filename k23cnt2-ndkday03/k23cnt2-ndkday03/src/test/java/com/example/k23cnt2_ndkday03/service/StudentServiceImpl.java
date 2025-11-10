package com.example.k23cnt2_ndkday03.service;

import com.example.k23cnt2_ndkday03.dto.StudentDTO;
import com.example.k23cnt2_ndkday03.entity.Student;
import com.example.k23cnt2_ndkday03.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<StudentDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getById(Long id) {
        Student s = repository.findById(id).orElse(null);
        return s != null ? toDTO(s) : null;
    }

    @Override
    public StudentDTO save(StudentDTO dto) {
        Student s = toEntity(dto);
        s = repository.save(s);
        return toDTO(s);
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) {
        Student s = repository.findById(id).orElse(null);
        if (s != null) {
            s.setName(dto.getName());
            s.setAge(dto.getAge());
            s.setGender(dto.getGender());
            s.setAddress(dto.getAddress());
            s.setEmail(dto.getEmail());
            s.setPhone(dto.getPhone());
            s = repository.save(s);
            return toDTO(s);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private StudentDTO toDTO(Student s) {
        StudentDTO dto = new StudentDTO();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setAge(s.getAge());
        dto.setGender(s.getGender());
        dto.setAddress(s.getAddress());
        dto.setEmail(s.getEmail());
        dto.setPhone(s.getPhone());
        return dto;
    }

    private Student toEntity(StudentDTO dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setAge(dto.getAge());
        s.setGender(dto.getGender());
        s.setAddress(dto.getAddress());
        s.setEmail(dto.getEmail());
        s.setPhone(dto.getPhone());
        return s;
    }
}