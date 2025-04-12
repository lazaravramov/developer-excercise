package com.devexercise.developer.Exception.Exceptions;

import com.devexercise.developer.Exception.Error.DeveloperExerciseError;

public class NotFoundException extends DeveloperExerciseException {
    public NotFoundException() {
        super(DeveloperExerciseError.NOT_FOUND_ERROR);
    }

    public NotFoundException( String detail) {
        super(DeveloperExerciseError.NOT_FOUND_ERROR, detail);
    }

    public NotFoundException( Throwable cause) {
        super(DeveloperExerciseError.NOT_FOUND_ERROR, cause);
    }

    public NotFoundException(String detail, Throwable cause) {
        super(DeveloperExerciseError.NOT_FOUND_ERROR, detail, cause);
    }
}
