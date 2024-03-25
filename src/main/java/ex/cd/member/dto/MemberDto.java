package ex.cd.member.dto;

import lombok.Data;

public class MemberDto {

    @Data
    public static class LoginIn {
        String email;
        String password;
    }
}
