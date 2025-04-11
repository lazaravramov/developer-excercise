package com.devexercise.developer.Exception;

import com.devexercise.developer.Exception.Error.DeveloperExerciseError;
import com.devexercise.developer.Exception.Exceptions.DeveloperExerciseException;
import com.devexercise.developer.Exception.Response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String exceptionMessage = "DeveloperExercise exception";
    @ExceptionHandler(value = {DeveloperExerciseException.class})
    protected ResponseEntity<Object> handleDeveloperExerciseException (DeveloperExerciseException developerExerciseException,
                                                            WebRequest webRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                developerExerciseException.getDeveloperExerciseError().getStatus().value(),
                exceptionMessage,
                developerExerciseException.getDetail()
        );
        return handleExceptionInternal(developerExerciseException,
                exceptionResponse,
                new HttpHeaders(),
                developerExerciseException.getDeveloperExerciseError().getStatus() ,
                webRequest);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGenericException (Exception exception,
                                                             WebRequest webRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                DeveloperExerciseError.GENERIC_SERVER_ERROR.getStatus().value(),
                DeveloperExerciseError.GENERIC_SERVER_ERROR.getMessage(),
                "Internal server error"
        );
        return handleExceptionInternal(exception,
                exceptionResponse,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }
}
