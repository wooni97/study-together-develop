package dev.flab.studytogether.domain.member.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class EmailAlreadyAuthenticatedException extends BadRequestException {
    public EmailAlreadyAuthenticatedException() {
        super("이미 인증된 이메일입니다.");
    }
}
