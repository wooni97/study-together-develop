package dev.flab.studytogether.domain.event.infrastructure.eventListener;

import dev.flab.studytogether.domain.event.EventListener;
import dev.flab.studytogether.domain.member.event.MemberV2SignUpEvent;
import dev.flab.studytogether.domain.member.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberV2SingUpEventListenerImpl implements EventListener<MemberV2SignUpEvent> {
    private final NotificationService notificationService;

    @Override
    @org.springframework.context.event.EventListener(MemberV2SignUpEvent.class)
    public void handleEvent(MemberV2SignUpEvent event) {
        String subject = "회원 가입 이메일 인증";
        String content = "<h1>[이메일 인증]</h1>" +
                "<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>" +
                "<a href='http://member/signUpConfirm?email=" +
                event.getUserEmail() +
                "&authKey=" +
                event.getAuthKey() +
                "' target='_blenk'>이메일 인증 확인</a>";
        notificationService.sendEmail(event.getUserEmail(), subject, content);
    }

}
