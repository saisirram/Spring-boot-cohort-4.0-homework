package com.sai.employee_management.dtos;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;

    @NotBlank(message = "Department Title cannot be blank")
    private String title;

    @AssertTrue(message = "Department should always be active")
    private Boolean active;

    @PastOrPresent(message = "Created date cannot be future")
    private LocalDate createdAt;
}
