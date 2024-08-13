package dev.flab.studytogether.domain.member.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class LinkNotValidException extends BadRequestException {
    public LinkNotValidException() {
        super("유효하지 않은 링크입니다.");
    }
}
