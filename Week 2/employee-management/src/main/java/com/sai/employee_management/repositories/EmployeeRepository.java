package com.sai.employee_management.repositories;

import com.sai.employee_management.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Optional can be omitted
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> getEmployeeByName(String name);
}
