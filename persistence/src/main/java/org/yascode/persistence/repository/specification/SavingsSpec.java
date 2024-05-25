package org.yascode.persistence.repository.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.yascode.persistence.entity.Savings;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SavingsSpec {

    public static Specification<Savings> totalSavingsBetween(String userId, Optional<YearMonth> startDate, Optional<YearMonth> endDate) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("income").get("user").get("id"), userId));

            startDate.ifPresent(start -> predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), start.atDay(1))));

            endDate.ifPresent(end -> predicates.add(cb.lessThan(root.get("startDate"), end.plusMonths(1).atDay(1))));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
