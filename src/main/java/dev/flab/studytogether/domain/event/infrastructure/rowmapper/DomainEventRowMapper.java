package dev.flab.studytogether.domain.event.infrastructure.rowmapper;

import dev.flab.studytogether.domain.event.DomainEvent;

public interface DomainEventRowMapper<T extends DomainEvent> {
    T createDomainEvent();
    Class<T> eventType();
}
