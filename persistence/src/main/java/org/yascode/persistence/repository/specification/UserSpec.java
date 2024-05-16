package org.yascode.persistence.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.yascode.persistence.entity.User;

public class UserSpec {

    public static Specification<User> userIdIs(final Long userId) {
        return (root, cq, cb) -> {
            if (userId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("id"), userId);
        };
    }

}
