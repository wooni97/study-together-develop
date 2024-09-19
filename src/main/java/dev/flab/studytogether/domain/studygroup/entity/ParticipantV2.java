package dev.flab.studytogether.domain.studygroup.entity;

import dev.flab.studytogether.domain.member.entity.MemberV2;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDY_GROUP_ID")
    private StudyGroup studyGroup;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberV2 memberV2;
    @Enumerated(EnumType.STRING)
    private ParticipantRole participantRole;
    private LocalDateTime joinedAt;

    private ParticipantV2(Long id, StudyGroup studyGroup, MemberV2 memberV2, ParticipantRole participantRole, LocalDateTime joinedAt) {
        this.id = id;
        this.studyGroup = studyGroup;
        this.memberV2 = memberV2;
        this.participantRole = participantRole;
        this.joinedAt = joinedAt;
    }

    private ParticipantV2(StudyGroup studyGroup, MemberV2 memberV2, ParticipantRole participantRole, LocalDateTime joinedAt) {
        this.studyGroup = studyGroup;
        this.memberV2 = memberV2;
        this.participantRole = participantRole;
        this.joinedAt = joinedAt;
        studyGroup.joinGroup(this);
    }

    protected ParticipantV2() {}

    public void changeRole(ParticipantRole participantRole) {
        this.participantRole = participantRole;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ParticipantV2 that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(studyGroup, that.studyGroup) && Objects.equals(memberV2, that.memberV2) && participantRole == that.participantRole && Objects.equals(joinedAt, that.joinedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studyGroup, memberV2, participantRole, joinedAt);
    }

    public static ParticipantV2 createExistingParticipant(Long id, StudyGroup studyGroup, MemberV2 memberV2, ParticipantRole participantRole, LocalDateTime joinedAt) {
        return new ParticipantV2(id, studyGroup, memberV2, participantRole, joinedAt);
    }

    public static ParticipantV2 createNewParticipant(StudyGroup studyGroup, MemberV2 member, ParticipantRole participantRole, LocalDateTime joinedAt) {
        return new ParticipantV2(studyGroup, member, participantRole, joinedAt);
    }
}
