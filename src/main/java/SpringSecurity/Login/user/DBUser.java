package SpringSecurity.Login.user;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String username;

    @Setter
    private String password;

    @Builder
    public DBUser(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

}
