package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.core.domain.studygroup.service.StudyGroupExitProcessStartService;
import dev.flab.studytogether.core.domain.event.EventRepository;
import dev.flab.studytogether.core.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.core.domain.studygroup.exception.ParticipantWithMemberIdNotFoundInGroupException;
import dev.flab.studytogether.core.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.core.domain.studygroup.repository.StudyGroupRepository;
import dev.flab.studytogether.util.TestFixtureUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    @DisplayName("Study Group 퇴장 이벤트 발행 시, 해당 참여자가 Study Group에 존재하지 않는 회원이면 예외 반환.")
    void testExitGroup() {
        //given
        Long roomId = 1L;
        Long participantId = 3L;
        Long memberId = 4L;

        //when
        StudyGroup studyGroup = TestFixtureUtils.randomStudyGroupWithParticipants();
        given(studyGroupRepository.findById(roomId))
                .willReturn(Optional.of(studyGroup));

        //then
        assertThrows(ParticipantWithMemberIdNotFoundInGroupException.class,
                () -> studyGroupExitProcessStartService.studyGroupExitProcessStart(roomId, participantId, memberId));
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
