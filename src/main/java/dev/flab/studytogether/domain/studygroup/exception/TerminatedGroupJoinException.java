package dev.flab.studytogether.domain.studygroup.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class TerminatedGroupJoinException extends BadRequestException {
    public TerminatedGroupJoinException(String message) {
        super(message);
    }
}
