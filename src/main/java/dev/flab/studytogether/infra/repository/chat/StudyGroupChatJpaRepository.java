package dev.flab.studytogether.infra.repository.chat;

import dev.flab.studytogether.core.domain.chat.entity.StudyGroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyGroupChatJpaRepository extends JpaRepository<StudyGroupChat, Long> {
    Optional<StudyGroupChat> findByStudyGroupId(Long studyGroupId);
}
