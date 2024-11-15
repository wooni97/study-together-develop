package dev.flab.studytogether.domain.chat.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private Long memberId;
    private String content;
    private LocalDateTime messageSentTime;

    protected ChatMessage() {}

    public ChatMessage(Long id, Long chatId, Long memberId, String content, LocalDateTime messageSentTime) {
        this.id = id;
        this.chatId = chatId;
        this.memberId = memberId;
        this.content = content;
        this.messageSentTime = messageSentTime;
    }
}
