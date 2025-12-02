package com.sai.employee_management.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private ApiErrorResponse errors;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiErrorResponse errors) {
        this();
        this.errors = errors;
    }

    public ApiResponse(T data, ApiErrorResponse errors, LocalDateTime timestamp) {
        this.data = data;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
}

