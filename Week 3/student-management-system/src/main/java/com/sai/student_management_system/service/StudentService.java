package com.sai.student_management_system.service;

import com.sai.student_management_system.entity.Student;
import com.sai.student_management_system.entity.Subject;
import com.sai.student_management_system.repository.StudentRepository;
import com.sai.student_management_system.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow();
    }

    public Student addSubjectsToStudent(String subjectName, Long studentId) {
        Set<Subject> subjects = subjectRepository.findByTitle(subjectName);
        Student student = studentRepository.findById(studentId).orElseThrow();
        Set<Subject> assignedSubjects = student.getSubjects();
        assignedSubjects.addAll(subjects);
        student.setSubjects(assignedSubjects);

        return studentRepository.save(student);

    }
}
