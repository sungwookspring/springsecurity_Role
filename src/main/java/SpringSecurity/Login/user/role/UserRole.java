package SpringSecurity.Login.user.role;

import SpringSecurity.Login.user.permission.UserPermission;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static SpringSecurity.Login.user.permission.UserPermission.*;
import static java.util.stream.Collectors.toMap;

@Getter
@Slf4j
public enum UserRole {
    GENERAL(Sets.newHashSet(BOOK_READ)),
    ADMIN(Sets.newHashSet(BOOK_READ , BOOK_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        log.info("getGrantedAuthorities 요청");
        Set<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }

    private static final Map<String, UserRole> stringToEnum =
            Stream.of(values()).collect(toMap(Objects::toString, e -> e));

    @JsonCreator
    public static UserRole fromString(String role){
        log.info("fromString 호출" + role);
        return stringToEnum.get(role);
    }

}
