package dev.flab.studytogether.domain.member.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class EmailAuthenticationExpiredException extends BadRequestException {
    public EmailAuthenticationExpiredException() {
        super("만료된 토큰입니다.");
    }
}
