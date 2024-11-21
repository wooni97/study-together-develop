package dev.flab.studytogether.websocket.response;

import lombok.Getter;

@Getter
public class WebSocketErrorResponse {

    private final int code;
    private final String message;
    private final Object data;

    private WebSocketErrorResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static WebSocketErrorResponse sendErrorMessage(int code, String message, Object data) {
        return new WebSocketErrorResponse(code, message, data);
    }
}
