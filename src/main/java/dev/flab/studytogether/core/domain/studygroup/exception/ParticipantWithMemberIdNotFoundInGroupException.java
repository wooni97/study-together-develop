package dev.flab.studytogether.core.domain.studygroup.exception;

import dev.flab.studytogether.common.exception.NotFoundException;

public class ParticipantWithMemberIdNotFoundInGroupException extends NotFoundException {
    public ParticipantWithMemberIdNotFoundInGroupException(Long studyGroupId, Long memberID) {
        super("Member Id : " + memberID + " Not Found in Study Group Id : " + studyGroupId);
    }
}
