package com.sai.employee_management.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceNotFoundException extends RuntimeException {

    private String message;
    private HttpStatus errorStatus;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
