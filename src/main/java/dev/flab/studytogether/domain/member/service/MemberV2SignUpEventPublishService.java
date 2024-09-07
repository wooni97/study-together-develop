package dev.flab.studytogether.domain.member.service;

import dev.flab.studytogether.domain.member.event.MemberV2SignUpEvent;
import dev.flab.studytogether.domain.member.repository.MemberV2SignUpEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberV2SignUpEventPublishService {
    private final MemberV2SignUpEventRepository memberV2SignUpEventRepository;

    public void publishEvent(MemberV2SignUpEvent event) {
        memberV2SignUpEventRepository.save(event);
    }
}
