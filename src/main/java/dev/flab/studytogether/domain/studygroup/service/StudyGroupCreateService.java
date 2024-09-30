package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.room.entity.ActivateStatus;
import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudyGroupCreateService {
    private final StudyGroupRepository studyGroupRepository;

    @Transactional
    public StudyGroup createGroup(String groupTitle, int maxParticipants, Long groupCreatorMemberId) {
        StudyGroup studyGroup = new StudyGroup(groupTitle,
                maxParticipants,
                ActivateStatus.ACTIVATED);

        ParticipantV2 participant = new ParticipantV2(studyGroup,
                groupCreatorMemberId,
                ParticipantV2.Role.GROUP_MANAGER,
                LocalDateTime.now());

        studyGroup.joinGroup(participant);
        studyGroupRepository.save(studyGroup);

        return studyGroup;
    }

}
