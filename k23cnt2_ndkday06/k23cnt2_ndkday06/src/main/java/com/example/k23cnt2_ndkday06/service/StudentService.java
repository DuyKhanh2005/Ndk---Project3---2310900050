package com.example.k23cnt2_ndkday06.service;

import com.example.k23cnt2_ndkday06.dto.StudentDTO;
import com.example.k23cnt2_ndkday06.entity.Student;
import com.example.k23cnt2_ndkday06.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<StudentDTO> findById(Long id) {
        return studentRepository.findById(id)
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .email(student.getEmail())
                        .age(student.getAge())
                        .build());
    }

    public boolean save(StudentDTO studentDTO) {
        try {
            Student student = Student.builder()
                    .name(studentDTO.getName())
                    .email(studentDTO.getEmail())
                    .age(studentDTO.getAge())
                    .build();
            studentRepository.save(student);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Student updateStudentById(Long id, StudentDTO updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setAge(updatedStudent.getAge());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}