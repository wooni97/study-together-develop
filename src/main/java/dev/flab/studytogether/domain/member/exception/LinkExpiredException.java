package dev.flab.studytogether.domain.member.exception;

import dev.flab.studytogether.exception.BadRequestException;

public class LinkExpiredException extends BadRequestException {
    public LinkExpiredException() {
        super("링크가 만료되었습니다.");
    }
}
