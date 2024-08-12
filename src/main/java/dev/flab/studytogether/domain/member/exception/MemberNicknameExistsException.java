package dev.flab.studytogether.domain.member.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class MemberNicknameExistsException extends BadRequestException {
    public MemberNicknameExistsException() {
        super("존재하는 닉네임입니다.");
    }
}
