package SpringSecurity.Login.user.permission;

import lombok.Getter;

@Getter
public enum UserPermission {
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }
}
