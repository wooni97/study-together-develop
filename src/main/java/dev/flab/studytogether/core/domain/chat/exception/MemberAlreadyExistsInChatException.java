package dev.flab.studytogether.core.domain.chat.exception;

import dev.flab.studytogether.common.exception.BadRequestException;
import lombok.Getter;

@Getter
public class MemberAlreadyExistsInChatException extends BadRequestException {

    private final Long chatId;
    private final Long memberId;

    public MemberAlreadyExistsInChatException(Long chatId, Long memberId) {
        super(String.format("chat id %d 에 member id %d가 이미 존재합니다", chatId, memberId));
        this.chatId = chatId;
        this.memberId = memberId;
    }
}
