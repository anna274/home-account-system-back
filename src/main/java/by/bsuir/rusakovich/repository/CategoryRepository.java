package by.bsuir.rusakovich.repository;

import by.bsuir.rusakovich.entity.Category;
import by.bsuir.rusakovich.entity.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT cc FROM Category cc WHERE cc.accountId = null AND cc.type = :type")
    List<Category> findAllByType(CategoryType type);

    @Query("SELECT cc FROM Category cc WHERE cc.accountId = :accountId AND cc.type = :type")
    List<Category> findAllCategoriesByAccountId(Long accountId, CategoryType type);
}
