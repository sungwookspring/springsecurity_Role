package SpringSecurity.Login.controller.user;

import SpringSecurity.Login.controller.user.dto.Request_User_JoinForm_dto;
import SpringSecurity.Login.controller.user.dto.Request_User_Login_dto;
import SpringSecurity.Login.controller.user.dto.Response_public_message;
import SpringSecurity.Login.security.jwt.JwtProvider;
import SpringSecurity.Login.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserAPI_Controller {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/join")
    public Long join(@RequestBody Request_User_JoinForm_dto request){
        Long saveId = userService.save(request);

        return saveId;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Request_User_Login_dto request) {
        try {
            // 인증정보 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            );

            // 인증
            Authentication authenticate = authenticationManager.authenticate(authentication);

            // 토큰 생성
            String token = jwtProvider.generateJwtToken(authenticate);

            return ResponseEntity
                    .ok(new Response_public_message(token));

        }catch(AuthenticationException e){
            log.error("[-] 인증 실패");
        }

        // To-do 예외처리 필요
        return ResponseEntity
                .badRequest()
                .body(new Response_public_message("login failed"));
    }
}
