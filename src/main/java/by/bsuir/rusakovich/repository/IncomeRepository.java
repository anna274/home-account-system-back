package by.bsuir.rusakovich.repository;

import by.bsuir.rusakovich.entity.Expense;
import by.bsuir.rusakovich.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT i FROM Income i INNER JOIN i.bankAccount ba WHERE ba.accountId = :accountId")
    List<Income> findAllIncomesByAccountId(Long accountId);

    @Query("SELECT i FROM Income i INNER JOIN i.bankAccount ba WHERE ba.accountId = :accountId AND i.date BETWEEN :startDate AND :endDate")
    List<Income> findAllByDate(long accountId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT i FROM Income i INNER JOIN i.bankAccount ba WHERE ba.accountId = :accountId AND i.category.id = :category AND ba.id = :bankAccount")
    List<Income> findAllByBankAccountIdAndCategoryId(Long bankAccount, Long category, Long accountId);

    @Query("SELECT i FROM Income i INNER JOIN i.bankAccount ba WHERE ba.accountId = :accountId AND ba.id = :bankAccount")
    List<Income> findAllByBankAccountId(Long bankAccount, Long accountId);

    @Query("SELECT i FROM Income i INNER JOIN i.bankAccount ba WHERE ba.accountId = :accountId AND i.category.id = :category")
    List<Income> findAllByCategoryId(Long category, Long accountId);

    @Query("SELECT i FROM Income i INNER JOIN i.bankAccount ba WHERE ba.accountId = :accountId AND i.date BETWEEN :startDate AND :endDate AND i.category.id = :category AND ba.id = :bankAccount")
    List<Income> findAllByBankAccountIdAndCategoryIdAndDate(Long accountId, Long bankAccount, Long category, LocalDate startDate, LocalDate endDate);

    @Query("SELECT i FROM Income i INNER JOIN i.bankAccount ba WHERE ba.accountId = :accountId AND i.date BETWEEN :startDate AND :endDate AND ba.id = :bankAccount")
    List<Income> findAllByBankAccountIdAndDate(Long accountId, Long bankAccount, LocalDate startDate, LocalDate endDate);

    @Query("SELECT i FROM Income i INNER JOIN i.bankAccount ba WHERE ba.accountId = :accountId AND i.date BETWEEN :startDate AND :endDate AND i.category.id = :category")
    List<Income> findAllByCategoryIdAndDate(Long accountId, Long category, LocalDate startDate, LocalDate endDate);
}
