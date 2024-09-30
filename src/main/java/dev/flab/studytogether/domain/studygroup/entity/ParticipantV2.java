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
    private Role participantRole;
    private LocalDateTime joinedAt;

    public ParticipantV2(StudyGroup studyGroup, Long memberId, Role participantRole, LocalDateTime joinedAt) {
        this.studyGroup = studyGroup;
        this.memberId = memberId;
        this.participantRole = participantRole;
        this.joinedAt = joinedAt;
    }

    protected ParticipantV2() {}

    public void changeRole(Role participantRole) {
        this.participantRole = participantRole;
    }

    @Getter
    @AllArgsConstructor
    public enum Role {
        GROUP_MANAGER("Study Group Manager"),
        ORDINARY_PARTICIPANT("Ordinary Participant");

        private final String roleDescription;
    }

}
