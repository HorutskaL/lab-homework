package com.epam.selectioncommittee.service.exception;

import com.epam.selectioncommittee.model.enums.ErrorType;

public class FacultyNotFoundException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Faculty is not found!";

    public FacultyNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
