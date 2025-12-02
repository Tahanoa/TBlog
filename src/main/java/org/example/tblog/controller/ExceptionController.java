package org.example.tblog.controller;

import org.example.tblog.dto.ExceptionDto;
import org.example.tblog.exception.NotFoundException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    private final MessageSourceAccessor messageSourceAccessor;

    public ExceptionController(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(
            NotFoundException ex,
            HttpServletRequest request
    ) {
        String localizedMessage = messageSourceAccessor.getMessage(
                ex.getMessage(),
                ex.getMessage()
        );

        ExceptionDto errorDto = new ExceptionDto(
                localizedMessage,
                "NOT_FOUND",
                request.getRequestURI(),
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDto>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ExceptionDto> errorDtos = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            String errorMessageKey = fieldError.getDefaultMessage();
            String localizedMessage = messageSourceAccessor.getMessage(
                    errorMessageKey,
                    errorMessageKey
            );

            ExceptionDto errorDto = new ExceptionDto(
                    localizedMessage,
                    "VALIDATION_ERROR",
                    request.getRequestURI(),
                    LocalDateTime.now(),
                    "Field: " + fieldError.getField()
            );
            errorDtos.add(errorDto);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDtos);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGeneralException(
            Exception ex,
            HttpServletRequest request
    ) {

        String errorMessage = messageSourceAccessor.getMessage(
                "error.general",
                "An unexpected error occurred"
        );

        ExceptionDto errorDto = new ExceptionDto(
                errorMessage,
                "INTERNAL_SERVER_ERROR",
                request.getRequestURI(),
                LocalDateTime.now(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}