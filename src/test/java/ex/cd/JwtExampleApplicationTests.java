package ex.cd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtExampleApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    void BCryptPasswordEncoderTest() {
        String password = "1234";
        System.out.println(passwordEncoder.encode(password));

        String password2 = "1234";
        System.out.println(passwordEncoder.encode(password2));
    }

}
