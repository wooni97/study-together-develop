package dev.flab.studytogether.domain.studygroup.entity;

import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.*;

@Embeddable
@NoArgsConstructor
public class ParticipantsV2 {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyGroup", orphanRemoval = true)
    private List<ParticipantV2> participants = new ArrayList<>();

    public ParticipantsV2(List<ParticipantV2> participants) {
        this.participants = participants;
    }

    public void addParticipant(ParticipantV2 participant) {
        participants.add(participant);
    }

    public int getCurrentJoinedParticipantsCount() {
        return (int) participants.stream()
                .filter(participant ->
                        ParticipantV2.ParticipantStatus.JOINED.equals(participant.getParticipantStatus()))
                .count();
    }

    public boolean isMemberJoined(Long memberId) {
        return participants.stream()
                    .anyMatch(participant -> participant.getMemberId().equals(memberId) &&
                            ParticipantV2.ParticipantStatus.JOINED.equals(participant.getParticipantStatus()));
    }

    public Optional<ParticipantV2> findJoinedParticipantByMemberId(Long memberId) {
        return participants.stream()
                .filter(participant -> memberId.equals(participant.getMemberId()) &&
                        ParticipantV2.ParticipantStatus.JOINED.equals(participant.getParticipantStatus()))
                .findFirst();
    }

    public Optional<ParticipantV2> findJoinedParticipantByParticipantId(Long participantId) {
        return participants.stream()
                .filter(participant -> participantId.equals(participant.getId()) &&
                        ParticipantV2.ParticipantStatus.JOINED.equals(participant.getParticipantStatus()))
                .findFirst();
    }

    public Optional<ParticipantV2> findNextManager() {
        return participants
                .stream()
                .filter(participant ->
                        !participant.getParticipantRole().equals(ParticipantV2.Role.GROUP_MANAGER) &&
                                ParticipantV2.ParticipantStatus.JOINED.equals(participant.getParticipantStatus()))
                .min(Comparator.comparing(ParticipantV2::getJoinedAt));
    }
}
