package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.event.EventRepository;
import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.event.StudyGroupJoinEvent;
import dev.flab.studytogether.domain.studygroup.exception.ParticipantWithMemberIdNotFoundInGroupException;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudyGroupJoinService {
    private final StudyGroupRepository studyGroupRepository;
    private final EventRepository eventRepository;

    @Transactional
    public ParticipantV2 joinGroup(Long roomId, Long memberId) {
        StudyGroup studyGroup = studyGroupRepository.findById(roomId)
                .orElseThrow(() -> new StudyGroupNotFoundException(roomId));

        studyGroup.joinGroup(new ParticipantV2(studyGroup,
                memberId,
                ParticipantV2.Role.ORDINARY_PARTICIPANT,
                ParticipantV2.ParticipantStatus.WAITING,
                LocalDateTime.now()));
        studyGroupRepository.save(studyGroup);

        ParticipantV2 joinedParticipant = studyGroup.getParticipants().getParticipants()
                .stream()
                .filter(p -> Objects.equals(p.getMemberId(), memberId))
                .findFirst()
                .orElseThrow(() -> new ParticipantWithMemberIdNotFoundInGroupException(roomId, memberId));

        StudyGroupJoinEvent studyGroupJoinEvent =
                StudyGroupJoinEvent.createNewEvent(studyGroup.getId(), joinedParticipant.getId());
        eventRepository.save(studyGroupJoinEvent);

        return joinedParticipant;
    }
}
