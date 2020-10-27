package SpringSecurity.Login.controller.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Response_Book_read_dto {
    private String response;

    @Builder
    public Response_Book_read_dto(String response) {
        this.response = response;
    }
}
