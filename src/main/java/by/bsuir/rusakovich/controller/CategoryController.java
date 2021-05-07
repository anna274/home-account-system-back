package by.bsuir.rusakovich.controller;

import by.bsuir.rusakovich.entity.Category;
import by.bsuir.rusakovich.entity.CategoryType;
import by.bsuir.rusakovich.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/income")
    public List<Category> getIncomeCategories(@RequestParam(name = "account_id", required = false) String accountId) {
        return findCategories(accountId, CategoryType.INCOME);
    }

    @GetMapping("/expense")
    public List<Category> getExpenseCategories(@RequestParam(name = "account_id", required = false) String accountId) {
        return findCategories(accountId, CategoryType.EXPENSE);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCategory(@PathVariable long id) {
        categoryRepository.deleteById(id);
    }

    private List<Category> findCategories(String accountId, CategoryType type) {
        List<Category> categories = categoryRepository.findAllByType(type);
        if (accountId != null) {
            Long accId = Long.parseLong(accountId);
            List<Category> customCategories = categoryRepository.findAllCategoriesByAccountId(accId, type);
            categories.addAll(customCategories);
        }
        return categories;
    }
}
