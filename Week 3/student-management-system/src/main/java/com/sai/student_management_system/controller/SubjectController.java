package com.sai.student_management_system.controller;

import com.sai.student_management_system.entity.Subject;
import com.sai.student_management_system.repository.SubjectRepository;
import com.sai.student_management_system.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/add")
    public ResponseEntity<?> addSubject(@RequestBody Subject subject) {
        return ResponseEntity.ok(subjectService.addSubject(subject));
    }

    @GetMapping("/addProfessor")
    public ResponseEntity<?> addProfessorToSubject(@RequestParam Long subjectId, @RequestParam Long professorId) {
        return ResponseEntity.ok(subjectService.addProfessorToSubject(subjectId, professorId));
    }

    @GetMapping
    public ResponseEntity<?> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }
}
