package ex.cd.member.controller;

import ex.cd.member.dto.MemberDto;
import ex.cd.member.dto.TokenDto;
import ex.cd.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인 API
     * @param loginIn
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberDto.LoginIn loginIn) {
        return ResponseEntity.ok(memberService.login(loginIn));
    }
}
