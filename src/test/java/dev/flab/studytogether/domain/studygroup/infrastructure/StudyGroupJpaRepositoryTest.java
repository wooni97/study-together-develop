package dev.flab.studytogether.domain.studygroup.infrastructure;

import dev.flab.studytogether.core.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.infra.repository.studygroup.StudyGroupJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
class StudyGroupJpaRepositoryTest {

    @Autowired
    private StudyGroupJpaRepository studyGroupJpaRepository;
    @Test
    void queryTest() {

        StudyGroup studyGroup = studyGroupJpaRepository.findById(41)
                .orElseThrow(() -> new NoSuchElementException());

        System.out.println(studyGroup.getParticipants().getCurrentJoinedParticipantsCount());
    }

}