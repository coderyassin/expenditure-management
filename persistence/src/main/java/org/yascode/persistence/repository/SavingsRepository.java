package org.yascode.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.yascode.persistence.entity.Savings;

public interface SavingsRepository extends JpaRepository<Savings, Long>, JpaSpecificationExecutor<Savings> {
}