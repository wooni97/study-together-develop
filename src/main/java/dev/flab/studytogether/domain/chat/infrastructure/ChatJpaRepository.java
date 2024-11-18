package dev.flab.studytogether.domain.chat.infrastructure;

import dev.flab.studytogether.domain.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatJpaRepository extends JpaRepository<Chat, Long> {
}
