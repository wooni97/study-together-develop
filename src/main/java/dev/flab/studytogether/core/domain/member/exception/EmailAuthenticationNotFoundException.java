package dev.flab.studytogether.core.domain.member.exception;

import dev.flab.studytogether.common.exception.NotFoundException;

public class EmailAuthenticationNotFoundException extends NotFoundException {
    public EmailAuthenticationNotFoundException() {
        super("존재하는 EmailAuthentication이 없습니다.");
    }
}
