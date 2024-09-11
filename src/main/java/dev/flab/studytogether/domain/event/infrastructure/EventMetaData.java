package dev.flab.studytogether.domain.event.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventMetaData {
    private final Long eventId;
}
