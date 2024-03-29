package ex.cd.member.service;

import ex.cd.member.dto.*;
import ex.cd.jwt.TokenProvider;
import ex.cd.member.model.Authority;
import ex.cd.member.model.Member;
import ex.cd.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인
     *
     * @param loginRequestDto
     * @return
     */
    @Transactional
    public TokenDto login(LoginRequestDto loginRequestDto) {
        // 1. email, password 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password());

        // 2. 사용자 검증
        // authenticate 메서드가 실행 될 때 CustomUserDetailsService 의 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 엑세스 토큰 생성
        String accessToken = tokenProvider.generateToken(authentication);
        return TokenDto.builder()
                .token(accessToken)
                .build();
    }

    /**
     * 이메일 중복 체크
     *
     * @param emailDto
     * @return
     */
    public boolean emailCheck(EmailCheckRequestDto emailDto) {
        return memberRepository.existsByEmail(emailDto.email());
    }

    /**
     * 회원가입
     *
     * @param joinDto
     * @return
     */
    @Transactional
    public boolean join(JoinRequestDto joinDto) {
        Member member = Member.builder()
                .email(joinDto.email())
                .password(passwordEncoder.encode(joinDto.password()))
                .authority(Authority.ROLE_USER)
                .build();
        memberRepository.save(member);
        return true;
    }
}
