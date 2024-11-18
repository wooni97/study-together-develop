package dev.flab.studytogether.domain.chat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatParticipant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private Long memberId;

    protected ChatParticipant() {}

    public ChatParticipant(Long chatId, Long memberId) {
        this.chatId = chatId;
        this.memberId = memberId;
    }
}
