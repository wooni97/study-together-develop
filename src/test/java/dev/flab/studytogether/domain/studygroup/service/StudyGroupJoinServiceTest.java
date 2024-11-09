package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.event.EventRepository;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import dev.flab.studytogether.util.TestFixtureUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StudyGroupJoinServiceTest {
    @InjectMocks
    private StudyGroupJoinService studyGroupJoinService;
    @Mock
    private StudyGroupRepository studyGroupRepository;
    @Mock
    private EventRepository eventRepository;

    @Test
    @DisplayName("Syudy Group에서 참가자가 입장할 때, 참가자 수가 증가")
    void testJoinGroup() {
        //given
        Long roomId = 1L;
        Long memberId = 1L;

        //when
        StudyGroup studyGroup = TestFixtureUtils.randomStudyGroup();
        given(studyGroupRepository.findById(roomId))
                .willReturn(Optional.of(studyGroup));

        int beforeExitParticipantsCount = studyGroup.getParticipants().getCurrentJoinedParticipantsCount();

        studyGroupJoinService.joinGroup(roomId, memberId);

        //then
        assertThat(studyGroup.getParticipants().getCurrentJoinedParticipantsCount())
                .isEqualTo(beforeExitParticipantsCount + 1);

    }

    @Test
    @DisplayName("존재하지 않는 Study Group Id인 경우 NoSuchElementException 예외 발생")
    void testJoinGroup2() {
        //given
        Long roomId = 1L;
        Long memberId = 1L;

        //when
        given(studyGroupRepository.findById(roomId))
                .willReturn(Optional.empty());

        //then
        assertThrows(StudyGroupNotFoundException.class,
                () -> studyGroupJoinService.joinGroup(roomId, memberId));
    }
}
