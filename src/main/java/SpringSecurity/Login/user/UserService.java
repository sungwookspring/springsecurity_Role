package SpringSecurity.Login.user;

import SpringSecurity.Login.controller.user.dto.Request_User_JoinForm_dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(Request_User_JoinForm_dto request){
        Optional<DBUser> find_user = userRepository.findByEmail(request.getEmail());
        // 이메일 중복검사
        if(find_user.isPresent()){
            return -1L;
        }else{
            DBUser new_user = request.toEntity();
            new_user.setPassword(passwordEncoder.encode(new_user.getPassword()));

            Long saveId = userRepository.save(request.toEntity()).getId();
            return saveId;
        }
    }
}
