package dev.flab.studytogether.domain.chat.entity;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@DiscriminatorValue("STUDY_GROUP_CHAT")
public class StudyGroupChat extends Chat {
    private Long studyGroupId;

    protected StudyGroupChat() {}

    public StudyGroupChat(Long id, LocalDateTime createdAt, Long studyGroupId) {
        super(id, createdAt);
        this.studyGroupId = studyGroupId;
    }
}
