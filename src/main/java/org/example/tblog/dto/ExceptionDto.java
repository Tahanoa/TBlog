package org.example.tblog.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionDto(
        String message,
        String errorCode,
        String path,
        LocalDateTime timestamp,
        String errorDetails
) {
    public ExceptionDto(String message, String errorCode, String path) {
        this(message, errorCode, path, LocalDateTime.now(), null);
    }

    public ExceptionDto(String message, String errorCode, String path, String errorDetails) {
        this(message, errorCode, path, LocalDateTime.now(), errorDetails);
    }
}