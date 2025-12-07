package com.sai.student_management_system.controller;

import com.sai.student_management_system.entity.AdmissionRecord;
import com.sai.student_management_system.service.AdmissionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admissions")
@RequiredArgsConstructor
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    @PostMapping("/add/{studentId}")
    public ResponseEntity<?> addAdmission(@PathVariable Long studentId, @RequestBody AdmissionRecord admissionRecord) {
        return ResponseEntity.ok(admissionRecordService.addAdmissionForStudent(admissionRecord, studentId));
    }
}
