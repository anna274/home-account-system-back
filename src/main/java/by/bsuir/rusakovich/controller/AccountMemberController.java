package by.bsuir.rusakovich.controller;

import by.bsuir.rusakovich.entity.Account;
import by.bsuir.rusakovich.entity.AccountMember;
import by.bsuir.rusakovich.repository.AccountMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account-members")
public class AccountMemberController {

    private final AccountMemberRepository accountMemberRepository;

    @GetMapping(value = "/{accountId}")
    public List<AccountMember> getAccountMembers(@PathVariable long accountId) {
            return accountMemberRepository.findAllByAccountId(accountId);
    }

    @PostMapping
    public AccountMember addAccountMember(@RequestBody AccountMember accountMember) {
        return accountMemberRepository.save(accountMember);
    }

    @PutMapping
    public AccountMember updateAccountMember(@RequestBody AccountMember accountMember) {
        AccountMember accountMemberToUpdate = accountMemberRepository.getOne(accountMember.getId());
        accountMemberToUpdate.setName(accountMember.getName());
        return accountMemberRepository.save(accountMemberToUpdate);
        //return accountMemberRepository.save(accountMember);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAccountMember(@PathVariable long id) {
        accountMemberRepository.deleteById(id);
    }
}
