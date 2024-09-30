package dev.flab.studytogether.domain.studygroup.exception;

import dev.flab.studytogether.exception.NotFoundException;

public class StudyGroupNotFoundException extends NotFoundException {
    public StudyGroupNotFoundException(Long studyGroupId) {
        super("Not Found Study Group Id : " + studyGroupId);
    }
}
