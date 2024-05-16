package org.yascode.persistence.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.yascode.persistence.entity.User;

import java.time.LocalDate;
import java.util.Optional;

public class UserSpec {

    public static Specification<User> userIdIs(final Long userId) {
        return (root, cq, cb) -> {
            if (userId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("id"), userId);
        };
    }

    public static Specification<User> usersRegisteredBetween(final Optional<LocalDate> startDate,
                                                             final Optional<LocalDate> endDate) {
        String creationDateAttribute = "creationDate";
        return (root, cq, cb) -> {
            if(endDate.isEmpty()) {
                if(startDate.isEmpty()) {
                    return cb.conjunction();
                }
                return cb.greaterThan(root.get(creationDateAttribute), startDate.get());
            } else if (startDate.isEmpty()) {
                return cb.lessThan(root.get(creationDateAttribute), endDate.get());
            }
             return cb.between(root.get(creationDateAttribute), startDate.get(), endDate.get());
        };
    }

}
