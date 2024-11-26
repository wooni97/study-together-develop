package dev.flab.studytogether.core.domain.event;

public interface EventPublish {
    void publish(DomainEvent event);
}
