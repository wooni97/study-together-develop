package dev.flab.studytogether.domain.chat.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long chatId;
    @NotNull
    private Long memberId;
    @NotNull
    private String memberNickName;
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime messageSentTime;

    protected ChatMessage() {}
}
