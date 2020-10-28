package SpringSecurity.Login.controller.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Response_public_message {
    private String message;

    public Response_public_message(String message) {
        this.message = message;
    }
}
