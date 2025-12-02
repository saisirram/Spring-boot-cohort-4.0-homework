package com.sai.employee_management.services;


import com.sai.employee_management.dtos.EmployeeDto;
import com.sai.employee_management.entities.Employee;
import com.sai.employee_management.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto>  getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).toList();
    }

    public Optional<EmployeeDto> getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(employee -> modelMapper.map(employee, EmployeeDto.class));
    }

    public EmployeeDto addEmployee(EmployeeDto employee) {
        Employee employeeToSave = modelMapper.map(employee, Employee.class);
        Employee savedEmployee = employeeRepository.save(employeeToSave);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setId(id);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto partialUpdateEmployee(Long id, Map<String, Object> updates) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(!employeeOptional.isPresent()) return null;

        updates.forEach((field, value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(Employee.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, employeeOptional.get(), value);
        });

        return modelMapper.map(employeeOptional.get(), EmployeeDto.class);
    }
}
