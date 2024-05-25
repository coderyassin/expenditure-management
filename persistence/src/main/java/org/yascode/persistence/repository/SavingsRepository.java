package org.yascode.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yascode.persistence.entity.Savings;

import java.util.List;

public interface SavingsRepository extends JpaRepository<Savings, Long>, JpaSpecificationExecutor<Savings> {

    @Query(value = "select s from Savings s where s.income.user.id = :userId")
    List<Savings> findByUserId(@Param("userId") Long userId);

}