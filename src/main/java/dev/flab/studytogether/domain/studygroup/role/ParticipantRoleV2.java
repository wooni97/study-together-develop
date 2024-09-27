package dev.flab.studytogether.domain.studygroup.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParticipantRoleV2 {
    GROUP_MANAGER("Study Group Manager"),
    ORDINARY_PARTICIPANT("Ordinary Participant");

    private final String roleDescription;
}
