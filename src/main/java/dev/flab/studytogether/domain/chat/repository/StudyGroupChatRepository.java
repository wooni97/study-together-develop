package dev.flab.studytogether.domain.chat.repository;

import dev.flab.studytogether.domain.chat.entity.StudyGroupChat;

import java.util.Optional;

public interface StudyGroupChatRepository {

    StudyGroupChat save(StudyGroupChat studyGroupChat);
    Optional<StudyGroupChat> findById(Long id);
    Optional<StudyGroupChat> findByStudyGroupId(Long studyGroupId);
}
