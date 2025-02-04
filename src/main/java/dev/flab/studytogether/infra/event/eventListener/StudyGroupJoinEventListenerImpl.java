package dev.flab.studytogether.infra.event.eventListener;

import dev.flab.studytogether.core.domain.event.EventListener;
import dev.flab.studytogether.core.domain.studygroup.event.StudyGroupJoinEvent;
import dev.flab.studytogether.core.domain.studygroup.exception.GroupCapacityExceededException;
import dev.flab.studytogether.core.domain.studygroup.exception.TerminatedGroupJoinException;
import dev.flab.studytogether.core.domain.studygroup.service.StudyGroupJoinService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class StudyGroupJoinEventListenerImpl implements EventListener<StudyGroupJoinEvent> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final StudyGroupJoinService studyGroupJoinService;

    @Override
    @org.springframework.context.event.EventListener(StudyGroupJoinEvent.class)
    public void handleEvent(StudyGroupJoinEvent event) {
        try {
            studyGroupJoinService.joinGroup(
                    event.getStudyGroupId(),
                    event.getMemberId());
        } catch(GroupCapacityExceededException | TerminatedGroupJoinException e) {
            log.info("error : {} message : {}", e.getClass(), e.getMessage());
        }
    }
}
