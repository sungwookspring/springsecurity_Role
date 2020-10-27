package SpringSecurity.Login.user;

import SpringSecurity.Login.controller.user.dto.Request_User_JoinForm_dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
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
            log.info("[*] 암호화전: " + new_user.getPassword());
            new_user.setPassword(passwordEncoder.encode(new_user.getPassword()));
            log.info("[*] 회원가입 전 패스워드 암호화 확인: " + new_user.getPassword());

            Long saveId = userRepository.save(new_user).getId();
            return saveId;
        }
    }


    public DBUser findByEmail(String email){
        DBUser find_user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("존재하지 않은 계정")
        );

        return find_user;
    }

    public DBUser findById(Long id){
        DBUser find_user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("존재하지 않은 계정")
        );

        return find_user;
    }

}

