package dev.flab.studytogether.core.domain.studygroup.service;

import dev.flab.studytogether.core.domain.event.EventPublish;
import dev.flab.studytogether.core.domain.room.entity.ActivateStatus;
import dev.flab.studytogether.core.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.core.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.core.domain.studygroup.event.StudyGroupCreatedEvent;
import dev.flab.studytogether.core.domain.studygroup.repository.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudyGroupCreateService {

    private final StudyGroupRepository studyGroupRepository;
    private final EventPublish eventPublish;

    @Transactional
    public StudyGroup createGroup(String groupTitle, int maxParticipants, Long groupCreatorMemberId) {
        StudyGroup studyGroup = new StudyGroup(groupTitle,
                maxParticipants,
                ActivateStatus.ACTIVATED);

        ParticipantV2 participant = new ParticipantV2(studyGroup,
                groupCreatorMemberId,
                ParticipantV2.Role.GROUP_MANAGER,
                ParticipantV2.ParticipantStatus.JOINED,
                LocalDateTime.now());

        studyGroup.joinGroup(participant);
        studyGroupRepository.save(studyGroup);

        StudyGroupCreatedEvent studyGroupCreatedEvent =
                StudyGroupCreatedEvent.createNewEvent(studyGroup.getId());

        eventPublish.publish(studyGroupCreatedEvent);

        return studyGroup;
    }

}
