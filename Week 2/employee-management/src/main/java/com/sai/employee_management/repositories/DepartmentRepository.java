package com.sai.employee_management.repositories;

import com.sai.employee_management.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
