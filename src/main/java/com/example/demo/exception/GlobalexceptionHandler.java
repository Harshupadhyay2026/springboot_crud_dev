package com.example.demo.exception;

import com.example.demo.dto.ExceptionDto;
import com.example.demo.dto.ValidationExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalexceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto>
    handleResourceNotFoundException(
            ResourceNotFoundException ex, HttpServletRequest request) {

        ExceptionDto exceptionResponse =new ExceptionDto();
        LocalDateTime.now();
        HttpStatus.NOT_FOUND.value();
        HttpStatus.NOT_FOUND.getReasonPhrase();
        ex.getMessage();
        request.getRequestURI();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(DuplicateREsourceException.class)
    public ResponseEntity<ExceptionDto>  handleDuplicateREsourceException(DuplicateREsourceException ex,HttpServletRequest Request) {

        ExceptionDto exceptionResponse =new ExceptionDto();
        LocalDateTime.now();
        HttpStatus.CONFLICT.value();
        HttpStatus.CONFLICT.getReasonPhrase();
        ex.getMessage();
        Request.getRequestURI();



        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity< ValidationExceptionResponseDto >
    handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(errors ->
                        fieldErrors.put(errors.getField(), errors.getDefaultMessage()));


        ValidationExceptionResponseDto exceptionResponse =new ValidationExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
               "VALIDATIONS FAILED",
                request.getRequestURI(),
                fieldErrors
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto>  handleRuntimeExceptio(
            RuntimeException ex,HttpServletRequest request)
    {
        ExceptionDto exceptionResponse =new ExceptionDto();
        LocalDateTime.now();
        HttpStatus.INTERNAL_SERVER_ERROR.value();
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        ex.getMessage();
        request.getRequestURI();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>  handleRuntimeExceptio(Exception ex)
    {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("some thing went wrong please try again later");
    }


}
