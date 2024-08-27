package dev.flab.studytogether.util;

import dev.flab.studytogether.domain.member.entity.MemberV2;

public class TestFixtureUtils {
    public static MemberV2 randomMember() {
        String email = "testEmail@gmail.com";
        String password = "password123";
        String nickname = "testNickName";

        return MemberV2.createNewMember(email, password, nickname);
    }
}
