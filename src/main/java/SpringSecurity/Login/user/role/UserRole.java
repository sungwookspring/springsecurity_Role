package SpringSecurity.Login.user.role;

import SpringSecurity.Login.user.permission.UserPermission;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

import static SpringSecurity.Login.user.permission.UserPermission.*;

@Getter
public enum UserRole {
    GENERAL(Sets.newHashSet(BOOK_READ)),
    ADMIN(Sets.newHashSet(BOOK_READ , BOOK_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
}
