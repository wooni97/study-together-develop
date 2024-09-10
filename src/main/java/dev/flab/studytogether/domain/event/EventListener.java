package dev.flab.studytogether.domain.event;

public interface EventListener<T extends DomainEvent> {
    void handleEvent(T event);
}
