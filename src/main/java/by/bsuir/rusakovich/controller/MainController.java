package by.bsuir.rusakovich.controller;

import by.bsuir.rusakovich.entity.Account;
import by.bsuir.rusakovich.entity.AccountRole;
import by.bsuir.rusakovich.repository.AccountRepository;
import by.bsuir.rusakovich.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public Account registration(String username, String password) {
        accountRepository.findByLogin(username).ifPresent(o -> {
            throw new SecurityException("Account with login=" + o.getLogin() + " already exists!");
        });

        Account account = new Account();
        String encodedPassword = passwordEncoder.encode(password);
        account.setLogin(username);
        account.setPassword(encodedPassword);
        account.setRoles(Collections.singleton(AccountRole.USER));
        return accountRepository.save(account);
    }

    @PostMapping("/successful-login")
    public UserDetailsImpl successfulLogin(@AuthenticationPrincipal UserDetailsImpl account) {
        return account;
    }
}
