package dev.flab.studytogether.domain.event.infrastructure.eventListener;

import dev.flab.studytogether.domain.event.EventListener;
import dev.flab.studytogether.domain.studygroup.event.StudyGroupJoinEvent;
import dev.flab.studytogether.domain.studygroup.service.StudyGroupJoinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class StudyGroupJoinEventListenerImpl implements EventListener<StudyGroupJoinEvent> {

    private final StudyGroupJoinService studyGroupJoinService;

    @Override
    @org.springframework.context.event.EventListener(StudyGroupJoinEvent.class)
    public void handleEvent(StudyGroupJoinEvent event) {
        studyGroupJoinService.joinGroup(
                event.getStudyGroupId(),
                event.getMemberId());
    }
}
