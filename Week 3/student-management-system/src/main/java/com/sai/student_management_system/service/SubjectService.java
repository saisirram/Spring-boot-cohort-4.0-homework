package com.sai.student_management_system.service;

import com.sai.student_management_system.entity.Professor;
import com.sai.student_management_system.entity.Subject;
import com.sai.student_management_system.repository.ProfessorRepository;
import com.sai.student_management_system.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject addProfessorToSubject(Long subjectId, Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow();
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();

        subject.setProfessor(professor);

        subjectRepository.save(subject);

        Set<Subject> existingSubjects = professor.getSubjects();
        existingSubjects.add(subject);
        professor.setSubjects(existingSubjects);
        professorRepository.save(professor);

        return subject;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow();
    }
}
