package dev.flab.studytogether.domain.room.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class GroupEntryException extends BadRequestException {
    public GroupEntryException(String message) {
        super(message);
    }
}
