package ex.cd.member.dto;

import jakarta.validation.constraints.Email;

public record EmailCheckRequestDto (
        @Email(message = "이메일 형식이 올바르지 않습니다")
        String email
) {}
