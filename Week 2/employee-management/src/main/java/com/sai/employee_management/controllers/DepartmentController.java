package com.sai.employee_management.controllers;

import com.sai.employee_management.dtos.DepartmentDto;
import com.sai.employee_management.exceptions.ResourceNotFoundException;
import com.sai.employee_management.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping
    ResponseEntity<?> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentDto> departmentDtoOptional = departmentService.getDepartmentById(id);
        return departmentDtoOptional.map(departmentDto -> ResponseEntity.ok(departmentDto))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.addDepartment(departmentDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateDepartmentById(@PathVariable Long id, @Valid @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDto, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.deleteDepartmentById(id));
    }
}
