package dev.flab.studytogether.domain.studygroup.entity;

import dev.flab.studytogether.domain.room.entity.ActivateStatus;
import dev.flab.studytogether.domain.room.entity.ParticipantRole;
import dev.flab.studytogether.domain.studygroup.exception.GroupCapacityExceededException;

import javax.persistence.*;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

@Entity
public class StudyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupTitle;
    private int maxParticipants;
    @Embedded
    private ParticipantsV2 participants;
    @Enumerated(EnumType.STRING)
    private ActivateStatus activateStatus;

    protected StudyGroup() {}

    public StudyGroup(String groupTitle, int maxParticipants, ActivateStatus activateStatus) {
        this.groupTitle = groupTitle;
        this.maxParticipants = maxParticipants;
        this.activateStatus = activateStatus;
        this.participants = new ParticipantsV2();
    }

    public void joinGroup(ParticipantV2 participant) {
        if(isMemberExists(participant))
            return;

        if(isGroupFull())
            throw new GroupCapacityExceededException("그룹 정원이 다 찼습니다.");

        participants.addParticipant(participant);
    }

    public StudyGroup exitGroup(ParticipantV2 participant) {
        if(participant.equals(participants.getRoomManger())) {
            changeGroupManager();
        }

        participants.removeParticipant(participant);
        return this;
    }

    public boolean isGroupFull() {
        return this.maxParticipants == participants.getCurrentParticipantsCount();
    }

    public void changeGroupManager(){
        ParticipantV2 currentRoomManager = participants.getRoomManger();
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
        return participants.getRoomManger().equals(participant);
    }

    public boolean isMemberExists(ParticipantV2 participant) {
        return participants.hasParticipant(participant);
    }

    public int getCurrentParticipantsCount() {
        return participants.getCurrentParticipantsCount();
    }

    public ParticipantV2 getGroupManager() {
        return participants.getRoomManger();
    }

    private void changeParticipantRole(ParticipantV2 participant, ParticipantRole roleToChange) {
        participants.getParticipants().stream()
                .filter(p -> p.equals(participant))
                .findFirst()
                .ifPresent(p -> p.changeRole(roleToChange));
    }

}
