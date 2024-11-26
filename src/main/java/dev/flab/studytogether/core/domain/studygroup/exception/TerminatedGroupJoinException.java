package dev.flab.studytogether.core.domain.studygroup.exception;

import dev.flab.studytogether.common.exception.BadRequestException;

public class TerminatedGroupJoinException extends BadRequestException {
    public TerminatedGroupJoinException(String message) {
        super(message);
    }
}
