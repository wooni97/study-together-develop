package dev.flab.studytogether.core.domain.studygroup.exception;

import dev.flab.studytogether.common.exception.BadRequestException;

public class MemberAlreadyExistsInGroupException extends BadRequestException {
    public MemberAlreadyExistsInGroupException(String message) {
        super(message);
    }
}
