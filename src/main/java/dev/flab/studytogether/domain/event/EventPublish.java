package dev.flab.studytogether.domain.event;

public interface EventPublish {
    void publish(DomainEvent event);
}
