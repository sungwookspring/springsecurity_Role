package SpringSecurity.Login.controller.book;

import SpringSecurity.Login.controller.book.dto.Response_Book_read_dto;
import SpringSecurity.Login.controller.book.dto.Response_Book_write_dto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Book_API_Controller {

    @GetMapping("/read")
    public Response_Book_read_dto read(){
        return Response_Book_read_dto.builder()
                .response("read success")
                .build();
    }

    @PutMapping("/write")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response_Book_write_dto write(){
        return Response_Book_write_dto.builder()
                .response("write success")
                .build();
    }
}
