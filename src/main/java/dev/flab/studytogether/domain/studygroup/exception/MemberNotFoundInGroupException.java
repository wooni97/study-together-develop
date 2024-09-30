package dev.flab.studytogether.domain.studygroup.exception;

import dev.flab.studytogether.exception.NotFoundException;

public class MemberNotFoundInGroupException extends NotFoundException {
    public MemberNotFoundInGroupException(Long studyGroupId, Long memberID) {
        super("Member Id : " + memberID + " Not Found in Study Group Id : " + studyGroupId);
    }
}
