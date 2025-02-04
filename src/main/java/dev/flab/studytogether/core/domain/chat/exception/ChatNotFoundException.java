package dev.flab.studytogether.core.domain.chat.exception;

import dev.flab.studytogether.common.exception.NotFoundException;
import lombok.Getter;

@Getter
public class ChatNotFoundException extends NotFoundException {

    private Long chatId;

    public ChatNotFoundException(Long chatId) {
        super("존재하지 않는 채팅방입니다.");
        this.chatId = chatId;
    }
}
