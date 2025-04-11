package com.devexercise.developer.Exception.Exceptions;

import com.devexercise.developer.Exception.Error.DeveloperExerciseError;

public class DeveloperExerciseException extends RuntimeException {
    private DeveloperExerciseError developerExerciseError;

    private String detail;

    public DeveloperExerciseException(DeveloperExerciseError developerExerciseError) {
        this.developerExerciseError = developerExerciseError;
    }

    public DeveloperExerciseException(DeveloperExerciseError developerExerciseError, String detail) {
        this.developerExerciseError = developerExerciseError;
        this.detail = detail;
    }

    public DeveloperExerciseException(DeveloperExerciseError developerExerciseError, Throwable cause) {
        super(developerExerciseError.getMessage(), cause);
        this.developerExerciseError = developerExerciseError;
    }

    public DeveloperExerciseException(DeveloperExerciseError developerExerciseError, String detail, Throwable cause) {
        super(developerExerciseError.getMessage(), cause);
        this.developerExerciseError = developerExerciseError;
        this.detail=detail;
    }

    public DeveloperExerciseError getDeveloperExerciseError() {
        return developerExerciseError;
    }

    public void setDeveloperExerciseError(DeveloperExerciseError developerExerciseError) {
        this.developerExerciseError = developerExerciseError;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
