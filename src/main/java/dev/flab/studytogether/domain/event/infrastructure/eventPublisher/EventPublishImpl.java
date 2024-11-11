package dev.flab.studytogether.domain.event.infrastructure.eventPublisher;

import dev.flab.studytogether.domain.event.DomainEvent;
import dev.flab.studytogether.domain.event.EventPublish;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EventPublishImpl implements EventPublish {
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
