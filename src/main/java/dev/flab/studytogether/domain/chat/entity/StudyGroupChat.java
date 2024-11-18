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

    public StudyGroupChat(LocalDateTime createdAt, Long studyGroupId) {
        super(createdAt);
        this.studyGroupId = studyGroupId;
    }

}
