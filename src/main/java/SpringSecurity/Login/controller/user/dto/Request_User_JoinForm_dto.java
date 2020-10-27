package SpringSecurity.Login.controller.user.dto;

import SpringSecurity.Login.user.DBUser;
import SpringSecurity.Login.user.role.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Request_User_JoinForm_dto {
    private String username;
    private String email;
    private String password;
    private UserRole role;

    @Builder
    public Request_User_JoinForm_dto(String username, String email, String password, UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public DBUser toEntity(){
        return DBUser.builder()
                .password(password)
                .username(username)
                .email(this.email)
                .build();
    }
}
