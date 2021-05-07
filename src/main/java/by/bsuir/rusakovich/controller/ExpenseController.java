package by.bsuir.rusakovich.controller;

import by.bsuir.rusakovich.entity.Expense;
import by.bsuir.rusakovich.entity.Income;
import by.bsuir.rusakovich.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseRepository expenseRepository;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    @PutMapping
    public Expense updateExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteExpense(@PathVariable long id) {
        expenseRepository.deleteById(id);
    }

    @GetMapping(value = "/{accountId}")
    public List<Expense> getExpenses(@PathVariable long accountId,
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
                    return expenseRepository.findAllByBankAccountIdAndCategoryIdAndDate(accountId, bankAccount, Long.parseLong(categoryId), sDate, eDate);
                }
                return expenseRepository.findAllByBankAccountIdAndDate(accountId, bankAccount, sDate, eDate);
            }

            if (categoryId != null) {
                return expenseRepository.findAllByCategoryIdAndDate(accountId, Long.parseLong(categoryId), sDate, eDate);
            }

            return expenseRepository.findAllByDate(accountId, sDate, eDate);
        }

        if (bankAccountId != null) {
            Long bankAccount = Long.parseLong(bankAccountId);
            if (categoryId != null) {
                return expenseRepository.findAllByBankAccountIdAndCategoryId(bankAccount, Long.parseLong(categoryId), accountId);
            }
            return expenseRepository.findAllByBankAccountId(bankAccount, accountId);
        }

        if (categoryId != null) {
            return expenseRepository.findAllByCategoryId(Long.parseLong(categoryId), accountId);
        }

        return expenseRepository.findAllExpensesByAccountId(accountId);
    }
}
