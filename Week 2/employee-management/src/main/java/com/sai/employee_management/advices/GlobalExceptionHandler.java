package com.sai.employee_management.advices;

import com.sai.employee_management.exceptions.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(NoSuchElementException exception) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorMessage(exception.getMessage())
                .build();
        //return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(
//                ApiErrorResponse.builder()
//                        .errorCode(HttpStatus.NOT_FOUND.value())
//                        .errorMessage(exception.getMessage())
//                        .build(), HttpStatus.NOT_FOUND);
        return buildApiErrorResponseEntity(apiErrorResponse);
    }

    // handles specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleCustomResourceNotFoundException(ResourceNotFoundException exception) {

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorCode(exception.getErrorStatus().value())
                .errorMessage(exception.getMessage())
                .build();

//        return new ResponseEntity<>(
//                ApiErrorResponse.builder()
//                        .errorCode(exception.getErrorStatus().value())
//                        .errorMessage(exception.getMessage())
//                        .build(), exception.getErrorStatus());

        return buildApiErrorResponseEntity(apiErrorResponse);
    }

    // handles all unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(exception.getMessage())
                .build();
//        return new ResponseEntity<>(
//                ApiErrorResponse.builder()
//                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        .errorMessage(exception.getMessage())
//                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        return buildApiErrorResponseEntity(apiErrorResponse);
    }

    // handles specific exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception.getBindingResult()
                .getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage("Input validation failed.")
                .subErrors(errorMessages)
                .build();

//        return new ResponseEntity<>(
//                ApiErrorResponse.builder()
//                        .errorCode(HttpStatus.BAD_REQUEST.value())
//                        .errorMessage("Input validation failed.")
//                        .subErrors(errorMessages)
//                        .build(), HttpStatus.BAD_REQUEST);
        return buildApiErrorResponseEntity(apiErrorResponse);
    }

    private ResponseEntity<ApiResponse<?>> buildApiErrorResponseEntity(ApiErrorResponse apiErrorResponse) {
        return new ResponseEntity<>(new ApiResponse<>(apiErrorResponse), HttpStatus.resolve(apiErrorResponse.getErrorCode()));
    }
}
