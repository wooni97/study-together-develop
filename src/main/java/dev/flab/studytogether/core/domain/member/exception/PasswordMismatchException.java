package dev.flab.studytogether.core.domain.member.exception;

import dev.flab.studytogether.common.exception.BadRequestException;

public class PasswordMismatchException extends BadRequestException {
    public PasswordMismatchException(String message) {
        super(message);
    }
}
