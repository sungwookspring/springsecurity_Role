package SpringSecurity.Login.controller.user;

import SpringSecurity.Login.controller.user.dto.Request_User_JoinForm_dto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
public class UserForm_Controller {

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("joinForm", new Request_User_JoinForm_dto());

        return "/user/join";
    }
}
