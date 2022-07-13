package ru.itis.og.security.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.og.model.Account;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class AccountUserDetailsImpl implements UserDetails {

    private final Account account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleAsAuthority = account.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleAsAuthority);
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.isNonBanned();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.isConfirmed();
    }

    public Account getAccount() {
        return account;
    }
}

