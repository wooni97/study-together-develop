package dev.flab.studytogether.domain.event.infrastructure.eventListener;

import dev.flab.studytogether.domain.chat.service.StudyGroupChatCreateService;
import dev.flab.studytogether.domain.event.EventListener;
import dev.flab.studytogether.domain.member.event.MemberV2SignUpEvent;
import dev.flab.studytogether.domain.studygroup.event.StudyGroupCreatedEvent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudyGroupCreatedEventListenerImpl implements EventListener<StudyGroupCreatedEvent> {

    private final StudyGroupChatCreateService studyGroupChatCreateService;

    public StudyGroupCreatedEventListenerImpl(StudyGroupChatCreateService studyGroupChatCreateService) {
        this.studyGroupChatCreateService = studyGroupChatCreateService;
    }


    @Override
    @org.springframework.context.event.EventListener(StudyGroupCreatedEvent.class)
    public void handleEvent(StudyGroupCreatedEvent event) {
        studyGroupChatCreateService.createStudyGroupChat(event.getStudyGroupId());
    }
}
