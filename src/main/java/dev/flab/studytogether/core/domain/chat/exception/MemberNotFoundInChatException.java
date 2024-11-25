package dev.flab.studytogether.core.domain.chat.exception;

import dev.flab.studytogether.common.exception.NotFoundException;
import lombok.Getter;

@Getter
public class MemberNotFoundInChatException extends NotFoundException {

    private final Long chatId;
    private final Long memberId;

    public MemberNotFoundInChatException(Long chatId, Long memberId) {
        super("채팅방에 존재하지 않는 회원입니다.");
        this.chatId = chatId;
        this.memberId = memberId;
    }
}
