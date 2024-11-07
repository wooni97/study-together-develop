package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.event.EventRepository;
import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.event.StudyGroupExitEvent;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StudyGroupExitProcessStartService {
    private final StudyGroupRepository studyGroupRepository;
    private final EventRepository eventRepository;

    public StudyGroup studyGroupExitProcessStart(Long roomId, Long memberId, Long participantId) {
        StudyGroup studyGroup = studyGroupRepository.findById(roomId)
                .orElseThrow(() -> new StudyGroupNotFoundException(roomId));

        ParticipantV2 exitingParticipant = studyGroup.getParticipantByMemberId(memberId);
        exitingParticipant.changeParticipatingStatus(
                ParticipantV2.ParticipantStatus.EXIT_WAITING);
        StudyGroupExitEvent studyGroupExitEvent =
                StudyGroupExitEvent.createNewEvent(studyGroup.getId(), participantId);

        studyGroupRepository.save(studyGroup);
        eventRepository.save(studyGroupExitEvent);

        return studyGroup;
    }
}
