package by.bsuir.rusakovich.security;

import by.bsuir.rusakovich.entity.Account;
import by.bsuir.rusakovich.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException("Account with login=" + login + " not found!"));
        return UserDetailsImpl.builder()
                .id(account.getId())
                .login(account.getLogin())
                .password(account.getPassword())
                .grantedAuthorities(new HashSet<>(account.getRoles()))
                .build();
    }
}
