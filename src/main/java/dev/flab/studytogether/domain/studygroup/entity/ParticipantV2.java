package dev.flab.studytogether.domain.studygroup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Where(clause = "participant_status = JOINED")
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
    @Enumerated(EnumType.STRING)
    private ParticipantStatus participantStatus;
    private LocalDateTime joinedAt;

    public ParticipantV2(StudyGroup studyGroup,
                         Long memberId,
                         Role participantRole,
                         ParticipantStatus participantStatus,
                         LocalDateTime joinedAt) {
        this.studyGroup = studyGroup;
        this.memberId = memberId;
        this.participantRole = participantRole;
        this.participantStatus = participantStatus;
        this.joinedAt = joinedAt;
    }

    protected ParticipantV2() {}

    public void changeRole(Role participantRole) {
        this.participantRole = participantRole;
    }

    public void changeParticipatingStatus(ParticipantStatus participantStatus) {
        this.participantStatus = participantStatus;
    }

    @Getter
    @AllArgsConstructor
    public enum Role {
        GROUP_MANAGER("Study Group Manager"),
        ORDINARY_PARTICIPANT("Ordinary Participant");

        private final String roleDescription;
    }

    @Getter
    @AllArgsConstructor
    public enum ParticipantStatus {
        WAITING("입장 대기"),
        JOINED("입장 완료"),
        LIMITED("입장 제한"),
        EXIT_WAITING("퇴장 대기"),
        EXITED("퇴장 완료");

        private final String description;
    }

}
