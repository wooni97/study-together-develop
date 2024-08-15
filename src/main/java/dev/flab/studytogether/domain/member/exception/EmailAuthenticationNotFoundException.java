package dev.flab.studytogether.domain.member.exception;

import dev.flab.studytogether.exception.NotFoundException;

public class EmailAuthenticationNotFoundException extends NotFoundException {
    public EmailAuthenticationNotFoundException() {
        super("존재하는 EmailAuthentication이 없습니다.");
    }
}
