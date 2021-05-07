package by.bsuir.rusakovich.repository;

import by.bsuir.rusakovich.entity.AccountMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMemberRepository extends JpaRepository<AccountMember, Long> {

    List<AccountMember> findAllByAccountId(long id);
}
