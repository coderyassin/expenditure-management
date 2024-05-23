package org.yascode.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.yascode.persistence.listener.SavingsListener;

import java.time.LocalDate;

@Entity
@Table(name = "Savings")
@EntityListeners(SavingsListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Savings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double amount;

    private String description;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @OneToOne
    @JoinColumn(name = "income_id")
    private Income income;
}
