package dev.flab.studytogether.domain.studygroup.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@NoArgsConstructor
@Getter
public class ParticipantsV2 {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "studyGroupId")
    private List<ParticipantV2> participants = new ArrayList<>();

    public ParticipantsV2(List<ParticipantV2> participants) {
        this.participants = participants;
    }

    public void addParticipant(ParticipantV2 participant) {
        participants.add(participant);
    }

    public void removeParticipant(ParticipantV2 participant) {
        participants.remove(participant);
    }

    public int getCurrentParticipantsCount() {
        return participants.size();
    }

    public boolean hasParticipant(ParticipantV2 participantV2) {
        return participants.contains(participantV2);
    }
}
