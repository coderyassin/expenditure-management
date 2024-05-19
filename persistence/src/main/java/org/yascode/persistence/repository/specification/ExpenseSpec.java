package org.yascode.persistence.repository.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.yascode.persistence.entity.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ExpenseSpec {


    public static Specification<Expense> expenseBetweenAlternative(final Optional<Long> idUser, Optional<LocalDate> startDate,
                                                        final Optional<LocalDate> endDate) {
        String userEntity = "user";
        String idAttribute = "id";
        String expenseDateAttribute = "expenseDate";
        return (root, cq, cb) -> {
            if(endDate.isEmpty()) {
                if(startDate.isEmpty()) {
                    return idUser.isEmpty() ? cb.conjunction() : cb.equal(root.get(userEntity).get(idAttribute), idUser.get());
                }
                return idUser.isEmpty() ? cb.greaterThan(root.get(expenseDateAttribute), startDate.get())
                                        : cb.and(cb.greaterThan(root.get(expenseDateAttribute), startDate.get()), cb.equal(root.get(userEntity).get(idAttribute), idUser.get()));
            } else if (startDate.isEmpty()) {
                return idUser.isEmpty() ? cb.lessThan(root.get(expenseDateAttribute), endDate.get())
                                        : cb.and(cb.lessThan(root.get(expenseDateAttribute), endDate.get()), cb.equal(root.get(userEntity).get(idAttribute), idUser.get()));
            }
            return idUser.isEmpty() ? cb.between(root.get(expenseDateAttribute), startDate.get(), endDate.get())
                                    : cb.and(cb.between(root.get(expenseDateAttribute), startDate.get(), endDate.get()), cb.equal(root.get(userEntity).get(idAttribute), idUser.get()));
        };
    }

    public static Specification<Expense> expenseBetween(final Optional<Long> idUser, final Optional<LocalDate> startDate,
                                                        final Optional<LocalDate> endDate) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            idUser.ifPresent(userId -> predicates.add(cb.equal(root.get("budget").get("user").get("id"), userId)));
            startDate.ifPresent(start -> predicates.add(cb.greaterThanOrEqualTo(root.get("expenseDate"), start)));
            endDate.ifPresent(end -> predicates.add(cb.lessThanOrEqualTo(root.get("expenseDate"), end)));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Expense> expenseBetween(final Optional<Long> idUser,
                                                        final Optional<LocalDate> startDate,
                                                        final Optional<LocalDate> endDate,
                                                        final Optional<String> categoryId) {

        Specification<Expense> categorySpec = (root, cq, cb) -> categoryId.map(catId -> cb.equal(root.get("category").get("id"), catId))
                .orElseGet(cb::conjunction);

        return expenseBetween(idUser, startDate, endDate).and(categorySpec);
    }

    public static Specification<Expense> filteringByCategory(String idUser, String categoryId, Optional<LocalDate> startDate, Optional<LocalDate> endDate) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("budget").get("user").get("id"), idUser));
            predicates.add(cb.equal(root.get("category").get("id"), categoryId));
            startDate.ifPresent(start -> predicates.add(cb.greaterThanOrEqualTo(root.get("expenseDate"), start)));
            endDate.ifPresent(end -> predicates.add(cb.lessThanOrEqualTo(root.get("expenseDate"), end)));

            return cb.and(predicates.toArray(new Predicate[0]));
        };


    }

    /*public static Specification<Expense> sumOfExpenses(final Optional<Long> idUser, final Optional<LocalDate> startDate,
                                                       final Optional<LocalDate> endDate) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            idUser.ifPresent(userId -> predicates.add(cb.equal(root.get("user").get("id"), userId)));
            startDate.ifPresent(start -> predicates.add(cb.greaterThanOrEqualTo(root.get("expenseDate"), start)));
            endDate.ifPresent(end -> predicates.add(cb.lessThanOrEqualTo(root.get("expenseDate"), end)));

            return cb.conjunction();
        };
    }*/
}
