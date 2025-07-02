package com.haidar.budgetplanner.repository;

import com.haidar.budgetplanner.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}