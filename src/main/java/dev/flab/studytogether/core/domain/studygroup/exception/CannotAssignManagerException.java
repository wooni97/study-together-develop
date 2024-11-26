package dev.flab.studytogether.core.domain.studygroup.exception;

import dev.flab.studytogether.common.exception.BadRequestException;

public class CannotAssignManagerException extends BadRequestException {
    public CannotAssignManagerException(String message) {
        super(message);
    }
}
