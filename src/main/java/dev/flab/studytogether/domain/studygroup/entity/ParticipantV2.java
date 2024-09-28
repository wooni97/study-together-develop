package dev.flab.studytogether.domain.studygroup.entity;

import lombok.AllArgsConstructor;
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
    private ParticipantRoleV2 participantRole;
    private LocalDateTime joinedAt;

    public ParticipantV2(StudyGroup studyGroup, Long memberId, ParticipantRoleV2 participantRole, LocalDateTime joinedAt) {
        this.studyGroup = studyGroup;
        this.memberId = memberId;
        this.participantRole = participantRole;
        this.joinedAt = joinedAt;
    }

    protected ParticipantV2() {}

    public void changeRole(ParticipantRoleV2 participantRole) {
        this.participantRole = participantRole;
    }

    @Getter
    @AllArgsConstructor
    public enum ParticipantRoleV2 {
        GROUP_MANAGER("Study Group Manager"),
        ORDINARY_PARTICIPANT("Ordinary Participant");

        private final String roleDescription;
    }

}
