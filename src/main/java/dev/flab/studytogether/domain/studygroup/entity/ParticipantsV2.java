package dev.flab.studytogether.domain.studygroup.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public void removeParticipant(Long participantId) {
        ParticipantV2 removeParticipant = participants.stream()
                .filter(participant -> participantId.equals(participant.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("현재 StudyGroup에 존재하지 않는 참여자입니다."));


        participants.remove(removeParticipant);
    }

    public int getCurrentParticipantsCount() {
        return participants.size();
    }

    public boolean hasParticipant(Long memberId) {
        return participants.stream()
                    .anyMatch(participant -> participant.getMemberId().equals(memberId));
    }
}
