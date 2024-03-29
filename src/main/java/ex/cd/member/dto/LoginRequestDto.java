package ex.cd.member.dto;

public record LoginRequestDto (
        String email,
        String password
) {}