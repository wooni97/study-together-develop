package dev.flab.studytogether.core.domain.event;


public interface EventRepository {
    void save(DomainEvent event);
}
