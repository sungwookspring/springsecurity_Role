package SpringSecurity.Login.user;

import SpringSecurity.Login.controller.user.dto.Request_User_JoinForm_dto;
import SpringSecurity.Login.user.role.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static SpringSecurity.Login.user.role.UserRole.ADMIN;
import static SpringSecurity.Login.user.role.UserRole.GENERAL;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired UserService userService;
    @Autowired PasswordEncoder passwordEncoder;

    @Test
    public void 회원가입_확인(){
        DBUser new_user = create_user("aaaaa", "tttttttt", "tesaaat@aaa.com", GENERAL);
    }

    private DBUser create_user(String username, String password, String email, UserRole role){
        //given
        Request_User_JoinForm_dto join_request = Request_User_JoinForm_dto.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(GENERAL)
                .build();

        //when
        Long saveId = userService.save(join_request);

        //then
        DBUser find_user = userService.findById(saveId);

        Assertions.assertThat(find_user.getEmail()).isEqualTo(join_request.getEmail());
        //암호화 확인
        Assertions.assertThat(passwordEncoder.matches(join_request.getPassword(), find_user.getPassword())).isTrue();

        return find_user;
    }
}