package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudyGroupCreateServiceTest {
    @InjectMocks
    private StudyGroupCreateService studyGroupCreateService;
    @Mock
    private StudyGroupRepository studyGroupRepository;

    @Test
    @DisplayName("Study Group 생성한 멤버가 Study Group의 매니저가 된다.")
    void testCreateGroup() {
        //given
        String groupTitle = "Java Study Group";
        int maxParticipants = 5;
        Long groupCreatorMemberId = 1L;

        //when
        StudyGroup createdGroup = studyGroupCreateService.createGroup(groupTitle, maxParticipants, groupCreatorMemberId);

        //then
        assertThat(createdGroup.getGroupManager().getMemberId())
                .isEqualTo(groupCreatorMemberId);
        
    }
}
