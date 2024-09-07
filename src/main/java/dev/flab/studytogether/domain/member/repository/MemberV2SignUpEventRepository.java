package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.event.MemberV2SignUpEvent;

import java.util.List;

public interface MemberV2SignUpEventRepository {
    void save(MemberV2SignUpEvent event);
    List<MemberV2SignUpEvent> findUnprocessedEvents();
    void updateAllToProcessed(List<Long> succeedEventIDs);
}
