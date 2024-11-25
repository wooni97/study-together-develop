package dev.flab.studytogether.domain.room.entity;

import static org.assertj.core.api.Assertions.*;

import dev.flab.studytogether.core.domain.room.entity.Participant;
import dev.flab.studytogether.core.domain.room.entity.ParticipantRole;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

class ParticipantTest {

    @Test
    @DisplayName("")
    void whenChangeRoleToRoomManager_Then() {
        //given
        long roomId = 1;
        int memberSequenceId = 1;
        ParticipantRole participantRole = ParticipantRole.ORDINARY_PARTICIPANT;
        LocalDateTime roomEntryTime = LocalDateTime.of(2024, 5, 1, 2, 23, 35);

        Participant participant = new Participant(roomId,
                                        memberSequenceId,
                                        participantRole,
                                        roomEntryTime);

        //when
        participant.changeRole(ParticipantRole.ROOM_MANAGER);

        //then
        assertThat(participant.getParticipantRole())
                .isEqualTo(ParticipantRole.ROOM_MANAGER);
    }
}