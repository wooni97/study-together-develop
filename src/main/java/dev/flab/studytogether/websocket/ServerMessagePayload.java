package dev.flab.studytogether.websocket;


import dev.flab.studytogether.websocket.enums.PayloadType;

public record ServerMessagePayload(PayloadType payloadType, Object data) {
}
