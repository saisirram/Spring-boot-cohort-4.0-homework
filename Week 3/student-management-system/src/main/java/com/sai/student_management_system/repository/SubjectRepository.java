package com.sai.student_management_system.repository;

import com.sai.student_management_system.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Set<Subject> findByTitle(String title);

    @Query(value = "select distinct student_id from student_subjects where subjects_id in :subjectIds", nativeQuery = true)
    List<Long> getStudentIdsFromSubjectIds(@Param("subjectIds") List<Long> subjectIds);
}