package dev.flab.studytogether.domain.event.infrastructure.eventListener;

import dev.flab.studytogether.domain.event.EventListener;
import dev.flab.studytogether.domain.studygroup.event.StudyGroupExitEvent;
import dev.flab.studytogether.domain.studygroup.service.StudyGroupExitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class StudyGroupExitEventListenerImpl implements EventListener<StudyGroupExitEvent> {

    private final StudyGroupExitService studyGroupExitService;

    @Override
    @org.springframework.context.event.EventListener(StudyGroupExitEvent.class)
    public void handleEvent(StudyGroupExitEvent event) {
        studyGroupExitService.exitGroup(
                event.getStudyGroupId(),
                event.getParticipantId());
    }
}
