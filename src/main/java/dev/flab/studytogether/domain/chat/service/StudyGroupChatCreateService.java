package dev.flab.studytogether.domain.chat.service;

import dev.flab.studytogether.domain.chat.entity.StudyGroupChat;
import dev.flab.studytogether.domain.chat.repository.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class StudyGroupChatCreateService {

    private final ChatRepository chatRepository;

    public StudyGroupChatCreateService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Transactional
    public void createStudyGroupChat(Long studyGroupId) {
        StudyGroupChat studyGroupChat = new StudyGroupChat(
                LocalDateTime.now()
                ,studyGroupId);

        chatRepository.save(studyGroupChat);
    }
}
