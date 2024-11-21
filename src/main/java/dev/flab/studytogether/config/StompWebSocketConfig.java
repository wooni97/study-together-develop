package dev.flab.studytogether.config;

import dev.flab.studytogether.domain.chat.service.ChatValidationService;
import dev.flab.studytogether.websocket.interceptor.ChatMemberCheckInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer{

    private final ChatValidationService chatValidationService;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/stomp-room")
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS();

        registry.addEndpoint("/chat")
                .setAllowedOrigins("*")
                .addInterceptors(new ChatMemberCheckInterceptor(chatValidationService))
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
           config.enableSimpleBroker("/queue", "/subscribe");
           config.setApplicationDestinationPrefixes("/publish");
    }



}
