package dev.flab.studytogether.domain.studygroup.exception;

import java.util.NoSuchElementException;

public class NoParticipantForManagerDelegateException extends NoSuchElementException {
    public NoParticipantForManagerDelegateException(String message) {
        super(message);
    }
}
