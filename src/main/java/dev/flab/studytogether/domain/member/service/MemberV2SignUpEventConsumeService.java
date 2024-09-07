package dev.flab.studytogether.domain.member.service;

import dev.flab.studytogether.domain.member.event.MemberV2SignUpEvent;
import dev.flab.studytogether.domain.member.repository.MemberV2SignUpEventRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MemberV2SignUpEventConsumeService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final MemberV2SignUpEventRepository memberV2SignUpEventRepository;
    private final NotificationService notificationService;

    @Scheduled(fixedDelay = 5000)
    public void consumeEvent() {
        List<MemberV2SignUpEvent> unprocessedEvents = memberV2SignUpEventRepository.findUnprocessedEvents();
        List<Long> succeedEventIDs = new ArrayList<>();

        unprocessedEvents
                .forEach(event -> {
                    try {
                        String subject = "회원 가입 이메일 인증";
                        String content = "<h1>[이메일 인증]</h1>" +
                                "<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>" +
                                "<a href='http://member/signUpConfirm?email=" +
                                event.getEmail() +
                                "&authKey=" +
                                event.getAuthKey() +
                                "' target='_blenk'>이메일 인증 확인</a>";

                        notificationService.sendEmail(event.getEmail(), subject, content);
                        succeedEventIDs.add(event.getId());
                    } catch (RuntimeException e) {
                        log.error("id : {} message : {}", event.getId(), e.getMessage());
                    }
                });

        memberV2SignUpEventRepository.updateAllToProcessed(succeedEventIDs);
    }
}
