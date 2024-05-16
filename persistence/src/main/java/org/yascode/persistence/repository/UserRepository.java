package org.yascode.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.yascode.persistence.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    List<User> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);
}
