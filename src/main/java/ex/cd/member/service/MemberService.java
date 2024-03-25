package ex.cd.member.service;

import ex.cd.member.dto.MemberDto;
import ex.cd.member.dto.TokenDto;
import ex.cd.jwt.TokenProvider;
import ex.cd.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * 로그인
     *
     * @param loginIn
     * @return
     */
    @Transactional
    public TokenDto login(MemberDto.LoginIn loginIn) {
        // 1. email, password 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginIn.getEmail(), loginIn.getPassword());

        // 2. 사용자 검증
        // authenticate 메서드가 실행 될 때 CustomUserDetailsService 의 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 엑세스 토큰 생성
        String accessToken = tokenProvider.generateToken(authentication);
        return TokenDto.builder()
                .token(accessToken)
                .build();
    }
}
