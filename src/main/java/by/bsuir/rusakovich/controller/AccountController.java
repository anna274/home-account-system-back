package by.bsuir.rusakovich.controller;

import by.bsuir.rusakovich.entity.Account;
import by.bsuir.rusakovich.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        return accountRepository.save(account);
    }

    @PutMapping("/password")
    public Account updateAccountPassword(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAccount(@PathVariable long id) {
        accountRepository.deleteById(id);
    }
}
