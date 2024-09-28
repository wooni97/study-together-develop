package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import dev.flab.studytogether.util.TestFixtureUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
class StudyGroupExitServiceTest {
    @InjectMocks
    private StudyGroupExitService studyGroupExitService;
    @Mock
    private StudyGroupRepository studyGroupRepository;

    @Test
    @DisplayName("Syudy Group에서 참가자가 나갈 때, 참가자 수가 감소")
    void testExitGroup() {
        //given
        Long roomId = 1L;
        Long participantId = 2L;
        Long memberId = 2L;

        //when
        StudyGroup studyGroup = TestFixtureUtils.randomStudyGroupWithParticipants();
        given(studyGroupRepository.findById(roomId))
                .willReturn(Optional.of(studyGroup));

        int beforeExitParticipantsCount = studyGroup.getParticipants().getCurrentParticipantsCount();

        StudyGroup resultStudyGroup = studyGroupExitService.exitGroup(roomId, participantId, memberId);

        //then
        assertThat(resultStudyGroup.getParticipants().getCurrentParticipantsCount())
                .isEqualTo(beforeExitParticipantsCount - 1);

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
        assertThrows(NoSuchElementException.class,
                () -> studyGroupExitService.exitGroup(roomId, participantId, memberId));
    }

    @Test
    @DisplayName("스터디 그룹 매니저가 나갈 때, 다음 참가자가 새로운 매니저로 설정된다.")
    void testManagerExitGroup() {
        //given
        Long roomId = 1L;
        Long managerParticipantId = 1L;
        Long memberId = 1L;
        Long nextManagerParticipantId = 2L;

        //when
        StudyGroup studyGroup = TestFixtureUtils.randomStudyGroupWithParticipants();
        given(studyGroupRepository.findById(roomId))
                .willReturn(Optional.of(studyGroup));

        StudyGroup resultStudyGroup = studyGroupExitService.exitGroup(roomId, managerParticipantId, memberId);

        //then
        assertThat(resultStudyGroup.getGroupManager().getId())
                .isEqualTo(nextManagerParticipantId);
    }
}
