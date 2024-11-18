package dev.flab.studytogether.domain.chat.exception;

import dev.flab.studytogether.exception.NotFoundException;

public class StudyGroupChatNotFoundException extends NotFoundException {
    public StudyGroupChatNotFoundException(Long studyGroupId) {
        super(String.format("Study Group Id %d에 채팅방에 존재하지 않습니다.", studyGroupId));
    }
}
