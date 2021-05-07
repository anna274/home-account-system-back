package by.bsuir.rusakovich.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum AccountRole implements GrantedAuthority {
    USER("USER_ROLE"),
    ADMIN("ADMIN_ROLE");

    private final String authorityName;

    @Override
    public String getAuthority() {
        return authorityName;
    }
}
