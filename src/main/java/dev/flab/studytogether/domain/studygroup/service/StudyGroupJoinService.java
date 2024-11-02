package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.MemberNotFoundInGroupException;
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

    @Transactional
    public ParticipantV2 joinGroup(Long roomId, Long memberId) {
        StudyGroup studyGroup = studyGroupRepository.findById(roomId)
                .orElseThrow(() -> new StudyGroupNotFoundException(roomId));

        ParticipantV2 participant = new ParticipantV2(studyGroup,
                memberId,
                ParticipantV2.Role.ORDINARY_PARTICIPANT,
                LocalDateTime.now());

        studyGroup.joinGroup(participant);
        studyGroupRepository.save(studyGroup);

        return studyGroup.getParticipants().getParticipants()
                .stream()
                .filter(p -> Objects.equals(p.getMemberId(), memberId))
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundInGroupException(roomId, memberId));
    }
}
