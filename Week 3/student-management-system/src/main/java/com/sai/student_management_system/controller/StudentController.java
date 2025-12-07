package com.sai.student_management_system.controller;

import com.sai.student_management_system.entity.Student;
import com.sai.student_management_system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/addSubject")
    public ResponseEntity<?> addSubjectsToStudent(@RequestParam String subjectName, @RequestParam Long studentId) {
        return ResponseEntity.ok(studentService.addSubjectsToStudent(subjectName, studentId));
    }
}
