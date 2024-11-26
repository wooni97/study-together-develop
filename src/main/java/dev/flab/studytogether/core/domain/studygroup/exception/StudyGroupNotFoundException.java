package dev.flab.studytogether.core.domain.studygroup.exception;

import dev.flab.studytogether.common.exception.NotFoundException;

public class StudyGroupNotFoundException extends NotFoundException {
    public StudyGroupNotFoundException(Long studyGroupId) {
        super("Not Found Study Group Id : " + studyGroupId);
    }
}
