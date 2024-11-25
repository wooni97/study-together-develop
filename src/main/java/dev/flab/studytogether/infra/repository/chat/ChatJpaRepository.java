package dev.flab.studytogether.infra.repository.chat;

import dev.flab.studytogether.core.domain.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatJpaRepository extends JpaRepository<Chat, Long> {
}
