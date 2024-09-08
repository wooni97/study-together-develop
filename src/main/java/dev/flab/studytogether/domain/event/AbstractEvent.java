package dev.flab.studytogether.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public abstract class AbstractEvent {
    private final LocalDateTime createdAt;
}
