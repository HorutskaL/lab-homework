package com.epam.selectioncommittee.service.exception;

import com.epam.selectioncommittee.model.enums.ErrorType;

public class StatementNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Statement is not found!";

    public StatementNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.PROCESSING_ERROR_TYPE;
    }

}
