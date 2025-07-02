package com.haidar.budgetplanner.repository;

import com.haidar.budgetplanner.entity.BudgetItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetItemRepository extends JpaRepository<BudgetItem, Long> {}