package org.yascode.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.yascode.persistence.listener.IncomeListener;
import org.yascode.shared.enumeration.Source;

import java.time.LocalDate;

@Entity
@Table(name = "incomes")
@EntityListeners(IncomeListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Source source;

    @NotNull
    @Column(name = "income_date")
    private LocalDate incomeDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "income")
    private Savings savings;

}
