package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.event.EventRepository;
import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import dev.flab.studytogether.util.TestFixtureUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StudyGroupExitProcessStartServiceTest {
    @InjectMocks
    private StudyGroupExitProcessStartService studyGroupExitProcessStartService;
    @Mock
    private StudyGroupRepository studyGroupRepository;
    @Mock
    private EventRepository eventRepository;

    @Test
    @DisplayName("Syudy Group에서 참가자가 나갈 때, 참가자의 상태가 EXIT_WAITING로 변경된다.")
    void testExitGroup() {
        //given
        Long roomId = 1L;
        Long participantId = 2L;
        Long memberId = 2L;

        //when
        StudyGroup studyGroup = TestFixtureUtils.randomStudyGroupWithParticipants();
        given(studyGroupRepository.findById(roomId))
                .willReturn(Optional.of(studyGroup));

        StudyGroup resultStudyGroup = studyGroupExitProcessStartService
                .studyGroupExitProcessStart(roomId, participantId, memberId);

        //then
        ParticipantV2 exitedParticipant = resultStudyGroup.getParticipantByParticipantId(participantId);
        assertThat(exitedParticipant.getParticipantStatus())
                .isEqualTo(ParticipantV2.ParticipantStatus.EXIT_WAITING);

    }

    @Test
    @DisplayName("존재하지 않는 Study Group Id인 경우 NoSuchElementException 예외 발생")
    void testExitGroup2() {
        //given
        Long roomId = 1L;
        Long participantId = 2L;
        Long memberId = 2L;

        //when
        given(studyGroupRepository.findById(roomId))
                .willReturn(Optional.empty());

        //then
        assertThrows(StudyGroupNotFoundException.class,
                () -> studyGroupExitProcessStartService.studyGroupExitProcessStart(roomId, participantId, memberId));
    }
}
