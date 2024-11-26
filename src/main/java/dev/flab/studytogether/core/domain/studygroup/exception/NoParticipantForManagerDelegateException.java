package dev.flab.studytogether.core.domain.studygroup.exception;

import java.util.NoSuchElementException;

public class NoParticipantForManagerDelegateException extends NoSuchElementException {
    public NoParticipantForManagerDelegateException(String message) {
        super(message);
    }
}
