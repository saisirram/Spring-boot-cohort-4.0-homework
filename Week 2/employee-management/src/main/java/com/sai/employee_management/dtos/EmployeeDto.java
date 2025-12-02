package com.sai.employee_management.dtos;

import com.sai.employee_management.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    Long id;
    @NotBlank(message = "Name of the employee cannot be empty.")
    @Size(min = 3, max = 15, message = "Name should have minimum 3 and maximum 15 characters")
    String name;

    @NotBlank
    @Email
    String email;

    @Max(value = 65, message = "Age cannot be greater than 65")
    @Min(value = 18, message = "Minimum age for an employee is 18")
    Integer age;

    @NotNull(message = "role cannot be null")
    // @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of employee must be USER or ADMIN")
    @EmployeeRoleValidation
    String role;

    @NotNull(message = "Salary cannot be null")
    @PositiveOrZero(message = "Employee salary should be greater than 0")
    @Digits(integer = 7, fraction = 2, message = "Salary cannot be greater than 7 digits and 2 decimals")
    @DecimalMin(value = "100000.00", message = "Employee should have a minimum of 1 lakh salary")
    @DecimalMax(value = "9999999.99", message = "Employee salary should not be greater that 99 lakhs.")
    Double salary;

    @PastOrPresent(message = "date of joining cannot be future date")
    LocalDate dateOfJoining;
    Boolean isActive;
}
