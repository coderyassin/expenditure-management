package org.yascode.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "budgets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    @NotNull
    private Double amount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "income_id", nullable = false)
    private Income income;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "budget_category", joinColumns = @JoinColumn(name = "budget_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categories;

    @OneToMany(mappedBy = "budget")
    private List<Expense> expenses;

}
