package dev.flab.studytogether.domain.studygroup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Where(clause = "is_participating = true")
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
    private Boolean isParticipating;
    private LocalDateTime joinedAt;

    public ParticipantV2(StudyGroup studyGroup, Long memberId, Role participantRole, LocalDateTime joinedAt) {
        this.studyGroup = studyGroup;
        this.memberId = memberId;
        this.participantRole = participantRole;
        this.isParticipating = true;
        this.joinedAt = joinedAt;
    }

    protected ParticipantV2() {}

    public void changeRole(Role participantRole) {
        this.participantRole = participantRole;
    }

    public void changeParticipatingStatus(Boolean status) {
        this.isParticipating = status;
    }

    @Getter
    @AllArgsConstructor
    public enum Role {
        GROUP_MANAGER("Study Group Manager"),
        ORDINARY_PARTICIPANT("Ordinary Participant");

        private final String roleDescription;
    }

}
