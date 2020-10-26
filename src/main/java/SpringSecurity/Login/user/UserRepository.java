package SpringSecurity.Login.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<DBUser, Long> {
    Optional<DBUser> findByEmail(String email);
}
