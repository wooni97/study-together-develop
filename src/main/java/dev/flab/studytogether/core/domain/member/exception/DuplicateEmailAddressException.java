package dev.flab.studytogether.core.domain.member.exception;

import dev.flab.studytogether.common.exception.BadRequestException;

public class DuplicateEmailAddressException extends BadRequestException {
    public DuplicateEmailAddressException() {
        super("이미 존재하는 이메일입니다.");
    }
}
