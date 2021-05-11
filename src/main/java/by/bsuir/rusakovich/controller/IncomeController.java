package by.bsuir.rusakovich.controller;

import by.bsuir.rusakovich.entity.BankAccount;
import by.bsuir.rusakovich.entity.Income;
import by.bsuir.rusakovich.repository.BankAccountRepository;
import by.bsuir.rusakovich.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/incomes")
public class IncomeController {

    private final IncomeRepository incomeRepository;
    private final BankAccountRepository bankAccountRepository;

    @PostMapping
    public Income addIncome(@RequestBody Income income) {
        BankAccount bankAccount = income.getBankAccount();
        bankAccount.setBalance(bankAccount.getBalance() + income.getSum());
        bankAccountRepository.save(bankAccount);
        return incomeRepository.save(income);
    }

    @PutMapping
    public Income updateIncome(@RequestBody Income income) {
        BankAccount bankAccount = income.getBankAccount();
        Income oldIncome = incomeRepository.getOne(income.getId());
        bankAccount.setBalance(bankAccount.getBalance() - oldIncome.getSum() + income.getSum());
        bankAccountRepository.save(bankAccount);
        return incomeRepository.save(income);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteIncome(@PathVariable long id) {
        Income income = incomeRepository.getOne(id);
        BankAccount bankAccount = income.getBankAccount();
        bankAccount.setBalance(bankAccount.getBalance() - income.getSum());
        bankAccountRepository.save(bankAccount);
        incomeRepository.deleteById(id);
    }

    @GetMapping(value = "/{accountId}")
    public List<Income> getIncomes(@PathVariable long accountId,
                                   @RequestParam(name = "startDate", required = false) String startDate,
                                   @RequestParam(name = "endDate", required = false) String endDate,
                                   @RequestParam(name = "bankAccountId", required = false) String bankAccountId,
                                   @RequestParam(name = "categoryId", required=false) String categoryId) {
        if (startDate != null) {
            LocalDate sDate = LocalDate.parse(startDate);
            LocalDate eDate = LocalDate.parse(endDate);

            if (bankAccountId != null) {
                Long bankAccount = Long.parseLong(bankAccountId);
                if (categoryId != null) {
                    return incomeRepository.findAllByBankAccountIdAndCategoryIdAndDate(accountId, bankAccount, Long.parseLong(categoryId), sDate, eDate);
                }
                return incomeRepository.findAllByBankAccountIdAndDate(accountId, bankAccount, sDate, eDate);
            }

            if (categoryId != null) {
                return incomeRepository.findAllByCategoryIdAndDate(accountId, Long.parseLong(categoryId), sDate, eDate);
            }

            return incomeRepository.findAllByDate(accountId, sDate, eDate);
        }

        if (bankAccountId != null) {
            Long bankAccount = Long.parseLong(bankAccountId);
            if (categoryId != null) {
                return incomeRepository.findAllByBankAccountIdAndCategoryId(bankAccount, Long.parseLong(categoryId), accountId);
            }
            return incomeRepository.findAllByBankAccountId(bankAccount, accountId);
        }

        if (categoryId != null) {
            return incomeRepository.findAllByCategoryId(Long.parseLong(categoryId), accountId);
        }

        return incomeRepository.findAllIncomesByAccountId(accountId);
    }
}
