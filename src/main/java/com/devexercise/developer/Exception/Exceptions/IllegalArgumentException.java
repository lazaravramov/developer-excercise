package com.devexercise.developer.Exception.Exceptions;

import com.devexercise.developer.Exception.Error.DeveloperExerciseError;

public class IllegalArgumentException extends DeveloperExerciseException {
    public IllegalArgumentException() {
        super(DeveloperExerciseError.ILLEGAL_ARGUMENTS_ERROR);
    }

    public IllegalArgumentException( String detail) {
        super(DeveloperExerciseError.ILLEGAL_ARGUMENTS_ERROR, detail);
    }

    public IllegalArgumentException( Throwable cause) {
        super(DeveloperExerciseError.ILLEGAL_ARGUMENTS_ERROR, cause);
    }

    public IllegalArgumentException(String detail, Throwable cause) {
        super(DeveloperExerciseError.ILLEGAL_ARGUMENTS_ERROR, detail, cause);
    }
}
