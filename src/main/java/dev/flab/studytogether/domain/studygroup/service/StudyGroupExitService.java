package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class StudyGroupExitService {
    private final StudyGroupRepository studyGroupRepository;

    public StudyGroup exitGroup(Long roomId, Long memberId, Long participantId) {
        StudyGroup studyGroup = studyGroupRepository.findById(roomId)
                .orElseThrow(() -> new StudyGroupNotFoundException(roomId));

        studyGroup.exitGroup(memberId, participantId);
        studyGroupRepository.save(studyGroup);

        return studyGroup;
    }
}
