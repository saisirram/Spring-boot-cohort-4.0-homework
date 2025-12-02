package com.sai.employee_management.advices;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiErrorResponse {
    Integer errorCode;
    String errorMessage;
    List<String> subErrors;
}
