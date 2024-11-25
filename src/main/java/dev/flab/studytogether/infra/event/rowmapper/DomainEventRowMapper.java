package dev.flab.studytogether.infra.event.rowmapper;

import dev.flab.studytogether.core.domain.event.DomainEvent;

public interface DomainEventRowMapper<T extends DomainEvent> {
    T createDomainEvent();
    Class<T> eventType();
}
