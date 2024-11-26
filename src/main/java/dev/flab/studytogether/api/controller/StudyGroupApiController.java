package dev.flab.studytogether.api.controller;

import dev.flab.studytogether.core.domain.studygroup.service.StudyGroupJoinService;
import dev.flab.studytogether.common.utils.SessionUtilV2;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/study-groups")
@AllArgsConstructor
public class StudyGroupApiController {

    private final StudyGroupJoinService studyGroupJoinService;

    @PostMapping("/v1/join")
    @Operation(summary = "Join Study Group", description = "스터디 그룹 참여")
    @ResponseStatus(HttpStatus.OK)
    public void join(
            @RequestParam(name = "groupId") Long groupId,
            HttpSession httpSession) {
        Long memberId = SessionUtilV2.getLoginMemberId(httpSession);
        studyGroupJoinService.joinGroup(groupId, memberId);
    }


}
