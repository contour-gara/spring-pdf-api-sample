package org.contourgara.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception) {
        log.warn(exception.getMessage(), exception);
        return ErrorResponse.create(exception, HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.create(exception, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
