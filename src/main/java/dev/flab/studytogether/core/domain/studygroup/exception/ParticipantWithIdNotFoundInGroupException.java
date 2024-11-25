package dev.flab.studytogether.core.domain.studygroup.exception;

import dev.flab.studytogether.common.exception.NotFoundException;

public class ParticipantWithIdNotFoundInGroupException extends NotFoundException {
    public ParticipantWithIdNotFoundInGroupException(Long studyGroupId, Long participantId) {
        super("Participant Id : " + participantId + " Not Found in Study Group Id : " + studyGroupId);
    }
}
