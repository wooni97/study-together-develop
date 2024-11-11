package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class StudyGroupJoinService {
    private final StudyGroupRepository studyGroupRepository;

    @Transactional
    public ParticipantV2 joinGroup(Long roomId, Long memberId) {
        StudyGroup studyGroup = studyGroupRepository.findById(roomId)
                .orElseThrow(() -> new StudyGroupNotFoundException(roomId));

        studyGroup.joinGroup(new ParticipantV2(studyGroup,
                memberId,
                ParticipantV2.Role.ORDINARY_PARTICIPANT,
                ParticipantV2.ParticipantStatus.JOINED,
                LocalDateTime.now()));
        studyGroupRepository.save(studyGroup);

        return studyGroup.getJoinedParticipantByMemberId(memberId);
    }
}
