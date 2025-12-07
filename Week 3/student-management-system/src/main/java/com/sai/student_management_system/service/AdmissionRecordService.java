package com.sai.student_management_system.service;

import com.sai.student_management_system.entity.AdmissionRecord;
import com.sai.student_management_system.entity.Student;
import com.sai.student_management_system.repository.AdmissionRecordRepository;
import com.sai.student_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;

    public AdmissionRecord addAdmissionForStudent(AdmissionRecord admissionRecord, Long student_id) {
        Student student = studentRepository.findById(student_id).orElseThrow();

        admissionRecord.setStudent(student);

        return admissionRecordRepository.save(admissionRecord);
    }
}
