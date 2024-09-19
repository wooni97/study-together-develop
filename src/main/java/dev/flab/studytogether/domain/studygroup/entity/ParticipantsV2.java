package dev.flab.studytogether.domain.studygroup.entity;

import dev.flab.studytogether.domain.room.entity.ParticipantRole;
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
    @OneToMany(mappedBy = "studyGroup", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
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

    public ParticipantV2 getRoomManger() {
        return participants.stream()
                .filter(participant ->
                        participant.getParticipantRole().equals(ParticipantRole.ROOM_MANAGER))
                .findFirst()
                .get();
    }

    public boolean hasParticipant(ParticipantV2 participantV2) {
        return participants.contains(participantV2);
    }
}
