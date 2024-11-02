package dev.flab.studytogether.websocket.enums;

import dev.flab.studytogether.exception.BadRequestException;

import java.util.Arrays;
import java.util.Objects;

public enum StompMessageType {
    JOIN("JOIN"),
    CHAT("CHAT");

    private final String messageType;

    StompMessageType(String messageType) {
        this.messageType = messageType;
    }

    public static StompMessageType findByMessageType(String messageType) {
        return Arrays.stream(StompMessageType.values())
                .filter(e -> Objects.equals(e.messageType, messageType))
                .findFirst()
                .orElseThrow(() -> new NoSuchStompMessageTypeException(messageType));
    }

    public static class NoSuchStompMessageTypeException extends BadRequestException {
        public NoSuchStompMessageTypeException(String messageType) {
            super("MessageType : " + messageType + " 존재하지 않음.");
        }
    }
}
