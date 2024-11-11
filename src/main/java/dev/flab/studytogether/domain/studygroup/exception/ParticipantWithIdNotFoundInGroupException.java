package dev.flab.studytogether.domain.studygroup.exception;

import dev.flab.studytogether.exception.NotFoundException;

public class ParticipantWithIdNotFoundInGroupException extends NotFoundException {
    public ParticipantWithIdNotFoundInGroupException(Long studyGroupId, Long participantId) {
        super("Participant Id : " + participantId + " Not Found in Study Group Id : " + studyGroupId);
    }
}
