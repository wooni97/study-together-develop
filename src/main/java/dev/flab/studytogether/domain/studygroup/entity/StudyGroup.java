package dev.flab.studytogether.domain.studygroup.entity;

import dev.flab.studytogether.domain.member.entity.MemberV2;
import dev.flab.studytogether.domain.room.entity.ActivateStatus;
import dev.flab.studytogether.domain.room.entity.ParticipantRole;
import dev.flab.studytogether.domain.studygroup.exception.GroupCapacityExceededException;
import dev.flab.studytogether.domain.studygroup.exception.MemberAlreadyExistsInGroupException;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

@Entity
@Getter
public class StudyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupTitle;
    private int maxParticipants;
    @Embedded
    private ParticipantsV2 participants;
    @OneToOne
    @JoinColumn(name = "MANAGER_PARTICIPANT_ID")
    private ParticipantV2 groupManager;
    @Enumerated(EnumType.STRING)
    private ActivateStatus activateStatus;

    protected StudyGroup() {}

    public StudyGroup(String groupTitle, int maxParticipants, MemberV2 creator, ActivateStatus activateStatus) {
        this.groupTitle = groupTitle;
        this.maxParticipants = maxParticipants;
        this.activateStatus = activateStatus;
        this.participants = new ParticipantsV2();
    }

    public void setGroupManager(MemberV2 creator) {
        if(this.id == null) {
            throw new IllegalStateException("스터디 그룹이 먼저 저장되어야 합니다.");
        }

        ParticipantV2 groupManager = ParticipantV2.createNewParticipant(
                this,
                creator.getId(),
                ParticipantRole.ROOM_MANAGER,
                LocalDateTime.now());
        this.groupManager = groupManager;

        joinGroup(groupManager);
    }

    public void joinGroup(ParticipantV2 participant) {
        if(isMemberExists(participant))
            throw new MemberAlreadyExistsInGroupException("이미 StudyGroup에 존재하는 유저입니다.");

        if(isGroupFull())
            throw new GroupCapacityExceededException("그룹 정원이 다 찼습니다.");

        participants.addParticipant(participant);
    }

    public StudyGroup exitGroup(ParticipantV2 participant) {
        if(participant.equals(groupManager)) {
            changeGroupManager();
        }

        participants.removeParticipant(participant);
        return this;
    }

    public boolean isGroupFull() {
        return this.maxParticipants == participants.getCurrentParticipantsCount();
    }

    public void changeGroupManager(){
        ParticipantV2 currentRoomManager = this.groupManager;
        Optional<ParticipantV2> nextRoomManager = findNextManager();

        if (nextRoomManager.isEmpty()) {
            throw new NoSuchElementException("방장 권한을 위임할 사용자가 존재하지 않습니다.");
        }

        changeParticipantRole(currentRoomManager, ParticipantRole.ORDINARY_PARTICIPANT);
        changeParticipantRole(nextRoomManager.get(), ParticipantRole.ROOM_MANAGER);
    }

    private Optional<ParticipantV2> findNextManager() {
        return participants.getParticipants()
                .stream()
                .filter(participant -> !participant.getParticipantRole().equals(ParticipantRole.ROOM_MANAGER))
                .min(Comparator.comparing(ParticipantV2::getJoinedAt));
    }

    public boolean isGroupManager(ParticipantV2 participant) {
        return this.groupManager.equals(participant);
    }

    public boolean isMemberExists(ParticipantV2 participant) {
        return participants.hasParticipant(participant);
    }

    public int getCurrentParticipantsCount() {
        return participants.getCurrentParticipantsCount();
    }

    private void changeParticipantRole(ParticipantV2 participant, ParticipantRole roleToChange) {
        participants.getParticipants().stream()
                .filter(p -> p.equals(participant))
                .findFirst()
                .ifPresent(p -> p.changeRole(roleToChange));
    }

}
