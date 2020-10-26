package SpringSecurity.Login.controller.user;

import SpringSecurity.Login.controller.user.dto.Request_User_JoinForm_dto;
import SpringSecurity.Login.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserAPI_Controller {
    private final UserService userService;

    @PostMapping("/join")
    public Long join(@RequestBody Request_User_JoinForm_dto request){
        Long saveId = userService.save(request);

        return saveId;
    }
}
