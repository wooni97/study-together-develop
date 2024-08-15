package dev.flab.studytogether.domain.member.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class DuplicateEmailAddressException extends BadRequestException {
    public DuplicateEmailAddressException() {
        super("이미 존재하는 이메일입니다.");
    }
}
