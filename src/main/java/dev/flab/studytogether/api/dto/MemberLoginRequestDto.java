package dev.flab.studytogether.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginRequestDto {
    private String email;
    private String password;

    private MemberLoginRequestDto() {}
}
