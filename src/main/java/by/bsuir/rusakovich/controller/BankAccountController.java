package by.bsuir.rusakovich.controller;

import by.bsuir.rusakovich.entity.BankAccount;
import by.bsuir.rusakovich.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

    private final BankAccountRepository bankAccountRepository;

    @GetMapping(value = "/{accountId}")
    public List<BankAccount> getBankAccounts(@PathVariable long accountId) {
        return bankAccountRepository.findAllByAccountId(accountId);
    }

    @PostMapping
    public BankAccount addBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping
    public BankAccount updateBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBankAccount(@PathVariable long id) {
        bankAccountRepository.deleteById(id);
    }
}
