package dev.flab.studytogether.domain.member.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class MemberEmailAddressExistsException extends BadRequestException {
    public MemberEmailAddressExistsException() {
        super("존재하는 이메일입니다.");
    }
}
