package dev.flab.studytogether.domain.event;


public interface EventRepository {
    void save(DomainEvent event);
}
