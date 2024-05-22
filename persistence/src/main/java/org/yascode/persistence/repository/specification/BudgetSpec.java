package org.yascode.persistence.repository.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.yascode.persistence.entity.Budget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BudgetSpec {

    public static Specification<Budget> budgetsBetween(Optional<String> idUser,
                                                       Optional<LocalDate> startDate,
                                                       Optional<LocalDate> endDate) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            idUser.ifPresent(userId -> predicates.add(cb.equal(root.get("income").get("user").get("id"), userId)));
            startDate.ifPresent(start -> predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), start)));
            endDate.ifPresent(end -> predicates.add(cb.lessThanOrEqualTo(root.get("endDate"), end)));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
