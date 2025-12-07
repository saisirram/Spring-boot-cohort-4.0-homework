package com.sai.student_management_system.controller;

import com.sai.student_management_system.entity.Professor;
import com.sai.student_management_system.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping("/add")
    public ResponseEntity<?> addProfessor(@RequestBody Professor professor) {
        return ResponseEntity.ok(professorService.addProfessor(professor));
    }

    @GetMapping
    public ResponseEntity<?> getAllProfessors() {
        return ResponseEntity.ok(professorService.getProfessors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfessorById(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }

    @GetMapping("/addStudents/{professorId}")
    public ResponseEntity<?> addAllStudentsToProfessor(@PathVariable Long professorId) {
        return ResponseEntity.ok(professorService.addStudentsToProfessor(professorId));
    }
}
