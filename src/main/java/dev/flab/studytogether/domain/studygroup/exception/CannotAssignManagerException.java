package dev.flab.studytogether.domain.studygroup.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class CannotAssignManagerException extends BadRequestException {
    public CannotAssignManagerException(String message) {
        super(message);
    }
}
