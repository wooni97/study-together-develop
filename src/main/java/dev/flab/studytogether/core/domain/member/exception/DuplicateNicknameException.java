package dev.flab.studytogether.core.domain.member.exception;

import dev.flab.studytogether.common.exception.BadRequestException;

public class DuplicateNicknameException extends BadRequestException {
    public DuplicateNicknameException() {
        super("존재하는 닉네임입니다.");
    }
}
