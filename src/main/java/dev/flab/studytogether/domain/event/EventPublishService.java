package dev.flab.studytogether.domain.event;

import dev.flab.studytogether.domain.event.AbstractEvent;

public interface EventPublishService {
    void publish(AbstractEvent event);
}
