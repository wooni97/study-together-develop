package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.event.EventRepository;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.event.StudyGroupExitEvent;
import dev.flab.studytogether.domain.studygroup.exception.ParticipantWithMemberIdNotFoundInGroupException;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class StudyGroupExitProcessStartService {
    private final StudyGroupRepository studyGroupRepository;
    private final EventRepository eventRepository;

    @Transactional
    public StudyGroup studyGroupExitProcessStart(Long roomId, Long memberId, Long participantId) {
        StudyGroup studyGroup = studyGroupRepository.findById(roomId)
                .orElseThrow(() -> new StudyGroupNotFoundException(roomId));

        if(!studyGroup.isMemberJoined(memberId)) {
            throw new ParticipantWithMemberIdNotFoundInGroupException(studyGroup.getId(), memberId);
        }

        StudyGroupExitEvent studyGroupExitEvent =
                StudyGroupExitEvent.createNewEvent(studyGroup.getId(), participantId);

        eventRepository.save(studyGroupExitEvent);

        return studyGroup;
    }
}
