package dev.flab.studytogether.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateRequestDto {
    private String id;
    private String password;
    private String nickname;

    private MemberCreateRequestDto() {}
}
