package dev.flab.studytogether.domain.event.infrastructure.eventListener;

import dev.flab.studytogether.domain.event.EventListener;
import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.event.StudyGroupJoinEvent;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudyGroupJoinEventListenerImpl implements EventListener<StudyGroupJoinEvent> {

    private final StudyGroupRepository studyGroupRepository;
    @Override
    @org.springframework.context.event.EventListener(StudyGroupJoinEvent.class)
    public void handleEvent(StudyGroupJoinEvent event) {
        StudyGroup studyGroup = studyGroupRepository.findById(event.getStudyGroupId())
                .orElseThrow(() -> new StudyGroupNotFoundException(event.getStudyGroupId()));

        ParticipantV2 participantV2 = studyGroup.getParticipantByParticipantId(event.getParticipantId());
        participantV2.changeParticipatingStatus(ParticipantV2.ParticipantStatus.JOINED);

        studyGroupRepository.save(studyGroup);
    }
}
