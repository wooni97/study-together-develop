package dev.flab.studytogether.domain.studygroup.entity;

import dev.flab.studytogether.domain.room.entity.ParticipantRole;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class ParticipantV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "study_group_id")
    private StudyGroup studyGroup;
    private Long memberId;
    @Enumerated(EnumType.STRING)
    private ParticipantRole participantRole;
    private LocalDateTime joinedAt;

    public ParticipantV2(StudyGroup studyGroup, Long memberId, ParticipantRole participantRole, LocalDateTime joinedAt) {
        this.studyGroup = studyGroup;
        this.memberId = memberId;
        this.participantRole = participantRole;
        this.joinedAt = joinedAt;
    }

    protected ParticipantV2() {}

    public void changeRole(ParticipantRole participantRole) {
        this.participantRole = participantRole;
    }

    public static ParticipantV2 createNewParticipant(StudyGroup studyGroup, Long memberId, ParticipantRole participantRole, LocalDateTime joinedAt) {
        return new ParticipantV2(studyGroup, memberId, participantRole, joinedAt);
    }
}
