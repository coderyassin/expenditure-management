package org.yascode.persistence.repository.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.yascode.persistence.entity.Income;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncomeSpec {
    public static Specification<Income> incomesByYearAndMonth(String userId, LocalDate startDate, LocalDate endDate) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(userId).ifPresent(idUser -> predicates.add(cb.equal(root.get("user").get("id"), idUser)));

            Optional.ofNullable(startDate).ifPresent(start -> predicates.add(cb.greaterThanOrEqualTo(root.get("incomeDate"), start)));

            Optional.ofNullable(endDate).ifPresent(end -> predicates.add(cb.lessThanOrEqualTo(root.get("incomeDate"), end)));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Income> incomesBetween(String userId, Optional<Year> yearOptional, Optional<Integer> startMonth, Optional<Integer> endMonth) {
        return (root, cq, cb) -> {

            Year year = yearOptional.isPresent() ? yearOptional.get() : Year.now();

            LocalDate start = startMonth.isPresent() ? LocalDate.of(year.getValue(), startMonth.get(), 1):
                    LocalDate.of(year.getValue(), LocalDate.now().getMonthValue(), 1);

            LocalDate end = endMonth.isPresent() ? LocalDate.of(year.getValue(), endMonth.get(), YearMonth.of(LocalDate.now().getYear(), endMonth.get()).lengthOfMonth()):
                    LocalDate.of(year.getValue(), LocalDate.now().getMonthValue(), YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth()).lengthOfMonth());

            List <Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("user").get("id"), userId));

            predicates.add(cb.greaterThanOrEqualTo(root.get("incomeDate"), start));

            predicates.add(cb.lessThanOrEqualTo(root.get("incomeDate"), end));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
