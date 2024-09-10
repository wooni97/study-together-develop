package dev.flab.studytogether.domain.event.infrastructure;

import dev.flab.studytogether.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventDto {
    private final EventMetaData metaData;
    private final DomainEvent event;
}
