package dev.flab.studytogether.domain.studygroup.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.*;

@Embeddable
@NoArgsConstructor
@Getter
public class ParticipantsV2 {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyGroup", orphanRemoval = true)
    private List<ParticipantV2> participants = new ArrayList<>();

    public ParticipantsV2(List<ParticipantV2> participants) {
        this.participants = participants;
    }

    public void addParticipant(ParticipantV2 participant) {
        participants.add(participant);
    }

    public int getCurrentParticipantsCount() {
        return participants.size();
    }

    public boolean hasParticipant(Long memberId) {
        return participants.stream()
                    .anyMatch(participant -> participant.getMemberId().equals(memberId));
    }

    public Optional<ParticipantV2> findParticipantByMemberId(Long memberId) {
        return participants.stream()
                .filter(participant -> memberId.equals(participant.getMemberId()))
                .findFirst();
    }

    public Optional<ParticipantV2> findParticipantByParticipantId(Long participantId) {
        return participants.stream()
                .filter(participant -> participantId.equals(participant.getId()))
                .findFirst();
    }
}
