package com.sai.employee_management.controllers;

import com.sai.employee_management.dtos.EmployeeDto;
import com.sai.employee_management.exceptions.ResourceNotFoundException;
import com.sai.employee_management.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/all")
    ResponseEntity<?> getAllEmployees() {
        //return ResponseEntity.ok(List.of(new EmployeeDto(1L, "Sai", "sai@gmail.com", 25, LocalDate.of(2021, 8, 5), true)));
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<?> getEmployeeById(@PathVariable(name = "id") Long employeeId) {
        // return ResponseEntity.ok(new EmployeeDto(employeeId, "Ram", "ram@grmail.com", 24, LocalDate.of(2021, 5, 21), true));
        Optional<EmployeeDto> employee = employeeService.getEmployeeById(employeeId);

//        return employee
//                .map(employeeDto -> ResponseEntity.ok(employeeDto))
//                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + employeeId));

        return employee
                .map(employeeDto -> ResponseEntity.ok(employeeDto))
                .orElseThrow(() -> ResourceNotFoundException.builder().errorStatus(HttpStatus.NOT_FOUND)
                        .message("Employee not found with id: " + employeeId)
                        .build());
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<?> handleEmployeeNotFoundException(NoSuchElementException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
//    }

    @PostMapping("/add")
    ResponseEntity<?> addEmployee(@RequestBody @Valid EmployeeDto employee) {
        EmployeeDto employeeDto = employeeService.addEmployee(employee);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(id, employeeDto));
    }

    @DeleteMapping("/{id}")
    void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @PatchMapping("/{id}")
    EmployeeDto updatePartialEmployee(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return employeeService.partialUpdateEmployee(id,updates);
    }
}
