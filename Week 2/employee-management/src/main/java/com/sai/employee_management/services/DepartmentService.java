package com.sai.employee_management.services;

import com.sai.employee_management.dtos.DepartmentDto;
import com.sai.employee_management.entities.Department;
import com.sai.employee_management.exceptions.ResourceNotFoundException;
import com.sai.employee_management.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .toList();
    }

    public Optional<DepartmentDto> getDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            return departmentOptional
                    .map(department -> modelMapper.map(department, DepartmentDto.class));
        } else {
            throw new ResourceNotFoundException("Department not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);

        Department savedDepartment = departmentRepository.save(department);

        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            Department departmentToSave = modelMapper.map(departmentDto, Department.class);
            departmentToSave.setId(id);
            Department savedDepartment = departmentRepository.save(departmentToSave);
            return modelMapper.map(savedDepartment, DepartmentDto.class);
        } else {
            throw new ResourceNotFoundException("Department not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public boolean deleteDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            departmentRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Department not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

}
