package dev.flab.studytogether.domain.chat.infrastructure;

import dev.flab.studytogether.domain.chat.entity.StudyGroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyGroupChatJpaRepository extends JpaRepository<StudyGroupChat, Long> {
    Optional<StudyGroupChat> findByStudyGroupId(Long StudyGroupId);
}
