package dev.flab.studytogether.domain.event;

import dev.flab.studytogether.domain.event.AbstractEvent;

public interface EventRepository {
    void save(AbstractEvent event);
}
