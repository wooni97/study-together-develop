package dev.flab.studytogether.core.domain.member.exception;

import dev.flab.studytogether.common.exception.BadRequestException;

public class EmailAlreadyAuthenticatedException extends BadRequestException {
    public EmailAlreadyAuthenticatedException() {
        super("이미 인증된 이메일입니다.");
    }
}
