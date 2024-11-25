package dev.flab.studytogether.infra.event.rowmapper;

import dev.flab.studytogether.core.domain.member.event.MemberV2SignUpEvent;
import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberV2SignUpEventRowMapper implements DomainEventRowMapper<MemberV2SignUpEvent> {
    private Long userId;
    private String userEmail;
    private String authKey;
    private LocalDateTime createdAt;

    @Override
    public MemberV2SignUpEvent createDomainEvent() {
        return MemberV2SignUpEvent.createFromExisting(userId, userEmail, authKey, createdAt);
    }

    @Override
    public Class<MemberV2SignUpEvent> eventType() {
        return MemberV2SignUpEvent.class;
    }
}
