package dev.flab.studytogether.core.domain.studygroup.entity;

import dev.flab.studytogether.core.domain.studygroup.exception.*;
import lombok.Getter;

import javax.persistence.*;

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
    @JoinColumn(name = "manager_participant_id")
    private ParticipantV2 groupManager;
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
        if(this.activateStatus.equals(ActivateStatus.TERMINATED)) {
            throw new TerminatedGroupJoinException("종료된 Study Group에는 입장할 수 없습니다.");
        }

        if(isMemberJoined(participant.getMemberId()))
            throw new MemberAlreadyExistsInGroupException("이미 StudyGroup에 존재하는 유저입니다.");

        if(isGroupFull())
            throw new GroupCapacityExceededException(
                    "Study Group ID : " + this.id +
                    " 정원이 다 찬 관계로 Member ID : " + participant.getMemberId() + " 입장 실패.");

        participants.addParticipant(participant);

        if(ParticipantV2.Role.GROUP_MANAGER.equals(participant.getParticipantRole())) {
            if(groupManager != null) throw new CannotAssignManagerException("Group Manager가 존재하는 그룹엔 매니저로 참여가 불가능합니다.");

            this.groupManager = participant;
        }
    }

    public void exitGroup(Long memberId, Long participantId) {
        if(!isMemberJoined(memberId)) {
            throw new ParticipantWithMemberIdNotFoundInGroupException(this.id, memberId);
        }

        if(isGroupManager(participantId)) {
            changeGroupManager();
        }

        ParticipantV2 exitingParticipant = getJoinedParticipantByMemberId(memberId);
        exitingParticipant.changeParticipatingStatus(ParticipantV2.ParticipantStatus.EXITED);
    }


    public boolean isGroupFull() {
        return this.maxParticipants <= participants.getCurrentJoinedParticipantsCount();
    }

    public void changeGroupManager(){
        ParticipantV2 currentRoomManager = this.groupManager;
        ParticipantV2 nextRoomManager = participants.findNextManager()
                .orElseThrow(() -> new NoParticipantForManagerDelegateException("방장 권한을 위임할 사용자가 존재하지 않습니다."));

        changeParticipantRole(currentRoomManager.getId(), ParticipantV2.Role.ORDINARY_PARTICIPANT);
        changeParticipantRole(nextRoomManager.getId(), ParticipantV2.Role.GROUP_MANAGER);

        this.groupManager = nextRoomManager;
    }

    public boolean isGroupManager(Long participantId) {
        return this.groupManager.getId().equals(participantId);
    }

    public boolean isMemberJoined(Long memberId) {
        return participants.isMemberJoined(memberId);
    }

    public ParticipantV2 getJoinedParticipantByMemberId(Long memberId) {
        return participants.findJoinedParticipantByMemberId(memberId)
                .orElseThrow(() ->
                        new ParticipantWithMemberIdNotFoundInGroupException(id, memberId));
    }

    public ParticipantV2 getJoinedParticipantByParticipantId(Long participantId) {
        return participants.findJoinedParticipantByParticipantId(participantId)
                .orElseThrow(() ->
                        new ParticipantWithIdNotFoundInGroupException(id, participantId));

    }

    private void changeParticipantRole(Long participantId, ParticipantV2.Role roleToChange) {
        getJoinedParticipantByParticipantId(participantId).changeRole(roleToChange);
    }

    public enum ActivateStatus {
        ACTIVATED,
        TERMINATED;
    }

}
