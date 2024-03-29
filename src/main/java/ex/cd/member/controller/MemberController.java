package ex.cd.member.controller;

import ex.cd.member.dto.EmailCheckRequestDto;
import ex.cd.member.dto.JoinRequestDto;
import ex.cd.member.dto.LoginRequestDto;
import ex.cd.member.dto.TokenDto;
import ex.cd.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "로그인 API", description = "로그인 테스트용 API")
@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "로그인", description = "테스트 계정(test@test.com / 1234)")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(memberService.login(loginRequestDto));
    }

    @Operation(summary = "이메일 중복 체크", description = "중복시 true, 중복 아닐시 false")
    @PostMapping("/join/emailCheck")
    public ResponseEntity<Boolean> emailCheck(@Valid @RequestBody EmailCheckRequestDto emailDto) {
        return ResponseEntity.ok(memberService.emailCheck(emailDto));
    }

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody JoinRequestDto joinDto) {
        try {
            memberService.join(joinDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
