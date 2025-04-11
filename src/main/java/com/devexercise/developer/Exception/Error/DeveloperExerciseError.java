package com.devexercise.developer.Exception.Error;

import org.springframework.http.HttpStatus;

public class DeveloperExerciseError {
    private HttpStatus status;
    private String message;

    public static final DeveloperExerciseError GENERIC_SERVER_ERROR = new DeveloperExerciseError(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Generic server error"
    );

    public static final DeveloperExerciseError ILLEGAL_ARGUMENTS_ERROR = new DeveloperExerciseError(
            HttpStatus.BAD_REQUEST,
            "Illegal arguments"
    ) ;

    public static final  DeveloperExerciseError NOT_FOUND_ERROR = new DeveloperExerciseError(
            HttpStatus.NOT_FOUND,
            "The record doesnt exist"
    );

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DeveloperExerciseError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
