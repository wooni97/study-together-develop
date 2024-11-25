package dev.flab.studytogether.core.domain.member.exception;

import dev.flab.studytogether.common.exception.BadRequestException;

public class EmailAuthenticationExpiredException extends BadRequestException {
    public EmailAuthenticationExpiredException() {
        super("만료된 토큰입니다.");
    }
}
