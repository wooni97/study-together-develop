package dev.flab.studytogether.domain.studygroup.entity;

import dev.flab.studytogether.domain.room.entity.ParticipantRole;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
public class ParticipantV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studyGroupId;
    private Long memberId;
    @Enumerated(EnumType.STRING)
    private ParticipantRole participantRole;
    private LocalDateTime joinedAt;

    private ParticipantV2(Long id, Long studyGroupId, Long memberId, ParticipantRole participantRole, LocalDateTime joinedAt) {
        this.id = id;
        this.studyGroupId = studyGroupId;
        this.memberId = memberId;
        this.participantRole = participantRole;
        this.joinedAt = joinedAt;
    }

    private ParticipantV2(Long studyGroupId, Long memberId, ParticipantRole participantRole, LocalDateTime joinedAt) {
        this.studyGroupId = studyGroupId;
        this.memberId = memberId;
        this.participantRole = participantRole;
        this.joinedAt = joinedAt;
    }

    protected ParticipantV2() {}

    public void changeRole(ParticipantRole participantRole) {
        this.participantRole = participantRole;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ParticipantV2 that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(studyGroupId, that.studyGroupId) && Objects.equals(memberId, that.memberId) && participantRole == that.participantRole && Objects.equals(joinedAt, that.joinedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studyGroupId, memberId, participantRole, joinedAt);
    }

    public static ParticipantV2 createExistingParticipant(Long id, Long studyGroupId, Long memberId, ParticipantRole participantRole, LocalDateTime joinedAt) {
        return new ParticipantV2(id, studyGroupId, memberId, participantRole, joinedAt);
    }

    public static ParticipantV2 createNewParticipant(StudyGroup studyGroup, Long memberId, ParticipantRole participantRole, LocalDateTime joinedAt) {
        return new ParticipantV2(studyGroup.getId(), memberId, participantRole, joinedAt);
    }
}
