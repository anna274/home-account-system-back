package by.bsuir.rusakovich.controller;

import by.bsuir.rusakovich.entity.Account;
import by.bsuir.rusakovich.entity.AccountRole;
import by.bsuir.rusakovich.repository.AccountRepository;
import by.bsuir.rusakovich.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public Page<Account> getAccounts(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @PostMapping
    public Account addAccount(@RequestBody Account account) {
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        return accountRepository.save(account);
    }

    @PutMapping
    public Account updateAccount(@RequestBody Account account) {
        Account accountToUpdate = accountRepository.getOne(account.getId());
        accountToUpdate.setRoles(account.getRoles());
        accountToUpdate.setLogin(account.getLogin());
        return accountRepository.save(accountToUpdate);
    }

    @PatchMapping("/password")
    public UserDetailsImpl updateAccountPassword(String oldPassword, String newPassword, @AuthenticationPrincipal UserDetailsImpl account) {
        Boolean passwordsMatch = passwordEncoder.matches(oldPassword, account.getPassword());
        if(passwordsMatch) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            Account accountToUpdate = accountRepository.getOne(account.getId());
            accountToUpdate.setPassword(encodedPassword);
            accountRepository.save(accountToUpdate);
            account.setPassword(encodedPassword);
        }
        return account;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAccount(@PathVariable long id) {
        accountRepository.deleteById(id);
    }
}
