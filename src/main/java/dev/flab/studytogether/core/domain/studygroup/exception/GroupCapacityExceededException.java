package dev.flab.studytogether.core.domain.studygroup.exception;

import dev.flab.studytogether.common.exception.BadRequestException;

public class GroupCapacityExceededException extends BadRequestException {
    public GroupCapacityExceededException(String message) {
        super(message);
    }
}
