package dev.flab.studytogether.core.domain.event;

public interface EventListener<T extends DomainEvent> {
    void handleEvent(T event);
}
