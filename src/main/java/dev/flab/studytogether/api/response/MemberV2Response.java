package dev.flab.studytogether.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberV2Response {
    private final Long memberId;
    private final String email;
    private final String nickname;
}
