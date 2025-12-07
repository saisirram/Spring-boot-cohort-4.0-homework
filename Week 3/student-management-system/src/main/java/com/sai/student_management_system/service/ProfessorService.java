package com.sai.student_management_system.service;

import com.sai.student_management_system.entity.Professor;
import com.sai.student_management_system.entity.Student;
import com.sai.student_management_system.entity.Subject;
import com.sai.student_management_system.repository.ProfessorRepository;
import com.sai.student_management_system.repository.StudentRepository;
import com.sai.student_management_system.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public Professor addProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> getProfessors() {
        return professorRepository.findAll();
    }

    public Professor getProfessorById(Long professorId) {
        return professorRepository.findById(professorId).orElseThrow();
    }

    public Professor addStudentsToProfessor(Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow();
        Set<Subject> subjects = professor.getSubjects();
        if (!subjects.isEmpty()) {
            List<Long> subjectIds = subjects.stream().map(Subject::getId).toList();
            List<Long> studentIds = subjectRepository.getStudentIdsFromSubjectIds(subjectIds);
            List<Student> students = studentRepository.findAllById(studentIds);
            Set<Student> existingStudents = professor.getStudents();
            existingStudents.addAll(students);
            professor.setStudents(existingStudents);

             return professorRepository.save(professor);
        } else {
            return professor;
        }
    }
}
