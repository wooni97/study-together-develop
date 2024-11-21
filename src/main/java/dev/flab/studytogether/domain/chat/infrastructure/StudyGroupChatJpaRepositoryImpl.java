package dev.flab.studytogether.domain.chat.infrastructure;

import dev.flab.studytogether.domain.chat.entity.StudyGroupChat;
import dev.flab.studytogether.domain.chat.repository.StudyGroupChatRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudyGroupChatJpaRepositoryImpl implements StudyGroupChatRepository {
    private final StudyGroupChatJpaRepository studyGroupChatJpaRepository;

    public StudyGroupChatJpaRepositoryImpl(StudyGroupChatJpaRepository studyGroupChatJpaRepository) {
        this.studyGroupChatJpaRepository = studyGroupChatJpaRepository;
    }

    @Override
    public StudyGroupChat save(StudyGroupChat studyGroupChat) {
        return studyGroupChatJpaRepository.save(studyGroupChat);
    }

    @Override
    public Optional<StudyGroupChat> findById(Long id) {
        return studyGroupChatJpaRepository.findById(id);
    }

    @Override
    public Optional<StudyGroupChat> findByStudyGroupId(Long studyGroupId) {
        return studyGroupChatJpaRepository.findByStudyGroupId(studyGroupId);
    }
}
