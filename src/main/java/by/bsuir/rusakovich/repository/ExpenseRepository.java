package by.bsuir.rusakovich.repository;

import by.bsuir.rusakovich.entity.Expense;
import by.bsuir.rusakovich.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e INNER JOIN e.bankAccount ba WHERE ba.accountId = :accountId")
    List<Expense> findAllExpensesByAccountId(Long accountId);

    @Query("SELECT e FROM Expense e INNER JOIN e.bankAccount ba WHERE ba.accountId = :accountId AND e.date BETWEEN :startDate AND :endDate")
    List<Expense> findAllByDate(long accountId, LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT e FROM Expense e INNER JOIN e.bankAccount ba WHERE ba.accountId = :accountId AND e.category.id = :category AND ba.id = :bankAccount")
    List<Expense> findAllByBankAccountIdAndCategoryId(Long bankAccount, Long category, Long accountId);

    @Query("SELECT e FROM Expense e INNER JOIN e.bankAccount ba WHERE ba.accountId = :accountId AND ba.id = :bankAccount")
    List<Expense> findAllByBankAccountId(Long bankAccount, Long accountId);

    @Query("SELECT e FROM Expense e INNER JOIN e.bankAccount ba WHERE ba.accountId = :accountId AND e.category.id = :category")
    List<Expense> findAllByCategoryId(Long category, Long accountId);

    @Query("SELECT e FROM Expense e INNER JOIN e.bankAccount ba WHERE ba.accountId = :accountId AND e.date BETWEEN :startDate AND :endDate AND e.category.id = :category AND ba.id = :bankAccount")
    List<Expense> findAllByBankAccountIdAndCategoryIdAndDate(Long accountId, Long bankAccount, Long category, LocalDate startDate, LocalDate endDate);

    @Query("SELECT e FROM Expense e INNER JOIN e.bankAccount ba WHERE ba.accountId = :accountId AND e.date BETWEEN :startDate AND :endDate AND ba.id = :bankAccount")
    List<Expense> findAllByBankAccountIdAndDate(Long accountId, Long bankAccount, LocalDate startDate, LocalDate endDate);

    @Query("SELECT e FROM Expense e INNER JOIN e.bankAccount ba WHERE ba.accountId = :accountId AND e.date BETWEEN :startDate AND :endDate AND e.category.id = :category")
    List<Expense> findAllByCategoryIdAndDate(Long accountId, Long category, LocalDate startDate, LocalDate endDate);
}
