package dev.flab.studytogether.domain.studygroup.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class MemberAlreadyExistsInGroupException extends BadRequestException {
    public MemberAlreadyExistsInGroupException(String message) {
        super(message);
    }
}
