package dev.flab.studytogether.core.domain.member.exception;


import dev.flab.studytogether.common.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
