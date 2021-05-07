package by.bsuir.rusakovich.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "account_member_id")
    private AccountMember accountMember;

    @Column(nullable = false, name = "account_id")
    private Long accountId;

    @Column(nullable = false, columnDefinition = "decimal(10,2)")
    private Double balance;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<Expense> expenses;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<Income> incomes;
}
