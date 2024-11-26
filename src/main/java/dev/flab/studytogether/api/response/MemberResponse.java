package dev.flab.studytogether.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberResponse {
    private final int sequenceId;
    private final String id;
    private final String nickname;
}
