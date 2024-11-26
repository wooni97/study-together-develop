package dev.flab.studytogether.api.controller;

import dev.flab.studytogether.api.dto.MemberLoginRequestDto;
import dev.flab.studytogether.api.response.MemberV2Response;
import dev.flab.studytogether.core.domain.member.entity.MemberV2;
import dev.flab.studytogether.core.domain.member.service.MemberV2Service;
import dev.flab.studytogether.common.utils.SessionUtilV2;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v2/members")
@AllArgsConstructor
public class MemberV2ApiController {
    private final MemberV2Service memberService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public MemberV2Response login(@RequestBody MemberLoginRequestDto memberLoginRequestDto,
                                  HttpSession httpSession) {
        MemberV2 member = memberService.login(memberLoginRequestDto.getEmail(), memberLoginRequestDto.getPassword());
        SessionUtilV2.setLoginMemberSession(httpSession, member.getId());

        return new MemberV2Response(member.getId(), member.getEmail(), member.getNickname());
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpSession httpSession) {
        SessionUtilV2.logout(httpSession);
    }
}
