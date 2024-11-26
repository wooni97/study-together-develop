package dev.flab.studytogether.infra.event;

import dev.flab.studytogether.core.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventDto {
    private final EventMetaData metaData;
    private final DomainEvent event;
}
