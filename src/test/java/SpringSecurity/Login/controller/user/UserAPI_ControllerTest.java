package SpringSecurity.Login.controller.user;

import SpringSecurity.Login.controller.user.dto.Request_User_JoinForm_dto;
import SpringSecurity.Login.user.UserRepository;
import SpringSecurity.Login.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAPI_ControllerTest {
    @LocalServerPort private int port;
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired TestRestTemplate restTemplate;

    @Test
    public void 회원가입_Dto사용(){
        //given
        Request_User_JoinForm_dto request = Request_User_JoinForm_dto.builder()
                .username("test_user")
                .password(passwordEncoder.encode("password"))
                .email("test@aaa.com")
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/user/join";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, request, Long.class);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    @Test
    public void 회원가입_중복(){
        //given
        Request_User_JoinForm_dto request = Request_User_JoinForm_dto.builder()
                .username("test_user")
                .password(passwordEncoder.encode("password"))
                .email("test@aaa.com")
                .build();

        //given
        Request_User_JoinForm_dto request2 = Request_User_JoinForm_dto.builder()
                .username("test_user")
                .password(passwordEncoder.encode("password"))
                .email("test@aaa.com")
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/user/join";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, request, Long.class);
        ResponseEntity<Long> responseEntity2 = restTemplate.postForEntity(url, request2, Long.class);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Assertions.assertThat(responseEntity2.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity2.getBody()).isLessThan(0L);
    }
}