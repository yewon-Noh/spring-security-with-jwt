package ex.cd.member.dto;

import lombok.Data;

public record LoginRequestDto (
        String email,
        String password
) {}