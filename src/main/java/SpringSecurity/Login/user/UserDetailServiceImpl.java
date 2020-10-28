package SpringSecurity.Login.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    /***
     * /login(Method: Post)요청 시 자동 호출
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("[*] request login email: " + email);
        DBUser find_user = userService.findByEmail(email);

        return UserDetailsImpl.builder()
                .user(find_user)
                .build();
    }
}
