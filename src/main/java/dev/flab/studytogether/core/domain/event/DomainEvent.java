package dev.flab.studytogether.core.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public abstract class DomainEvent {
    private final LocalDateTime createdAt;
}
