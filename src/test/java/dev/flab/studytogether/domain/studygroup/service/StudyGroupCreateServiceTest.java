package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.event.EventPublish;
import dev.flab.studytogether.domain.event.infrastructure.eventListener.StudyGroupCreatedEventListenerImpl;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.event.StudyGroupCreatedEvent;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyGroupCreateServiceTest {
    @InjectMocks
    private StudyGroupCreateService studyGroupCreateService;
    @Mock
    private StudyGroupRepository studyGroupRepository;
    @Mock
    private EventPublish eventPublish;


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

    @Test
    @DisplayName("이벤트 리스너에서 예외 발생 시 서비스 계층까지 전파된다.")
    void exceptionPropagationTest() {
        //given
        StudyGroupCreatedEventListenerImpl mockListener =
                mock(StudyGroupCreatedEventListenerImpl.class);

        doAnswer(invocation -> {
            StudyGroupCreatedEvent event = invocation.getArgument(0);
            // Mock 리스너 호출 및 예외 발생
            doThrow(new RuntimeException("Listener Exception"))
                    .when(mockListener).handleEvent(event);

            mockListener.handleEvent(event);
            return null;
        }).when(eventPublish).publish(any(StudyGroupCreatedEvent.class));


        String groupTitle = "Test Group";
        int maxParticipants = 10;
        Long groupCreatorMemberId = 1L;

        // When & Then: 서비스 호출 시 리스너 예외가 전파되는지 확인
        assertThatThrownBy(() ->
                studyGroupCreateService.createGroup(groupTitle, maxParticipants, groupCreatorMemberId)
        ).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Listener Exception");

    }
}
