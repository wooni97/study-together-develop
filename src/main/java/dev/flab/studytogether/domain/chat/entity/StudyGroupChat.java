package dev.flab.studytogether.domain.chat.entity;

import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
public class StudyGroupChat extends Chat {
    private Long studyGroupId;

    protected StudyGroupChat() {}

    public StudyGroupChat(Long id, LocalDateTime createdAt, Long studyGroupId) {
        super(id, createdAt);
        this.studyGroupId = studyGroupId;
    }
}
