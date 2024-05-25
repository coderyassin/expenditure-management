package org.yascode.persistence.repository.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.yascode.persistence.entity.Income;

import java.time.LocalDate;
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
}
