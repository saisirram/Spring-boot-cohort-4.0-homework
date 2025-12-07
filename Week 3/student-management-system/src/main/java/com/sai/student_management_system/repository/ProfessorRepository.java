package com.sai.student_management_system.repository;

import com.sai.student_management_system.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}