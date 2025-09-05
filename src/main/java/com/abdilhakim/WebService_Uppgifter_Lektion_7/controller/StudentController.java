package com.abdilhakim.WebService_Uppgifter_Lektion_7.controller;

import com.abdilhakim.WebService_Uppgifter_Lektion_7.model.Student;
import com.abdilhakim.WebService_Uppgifter_Lektion_7.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/getstudentbyid/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{firstName}/{lastName}")
    public ResponseEntity<List<Student>> getStudentsByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        List<Student> students = studentRepository.findByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(201).body(savedStudent);
    }

    @DeleteMapping("/deletestudentby/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}