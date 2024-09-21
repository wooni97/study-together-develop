package dev.flab.studytogether.domain.studygroup.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class GroupCapacityExceededException extends BadRequestException {
    public GroupCapacityExceededException(String message) {
        super(message);
    }
}
