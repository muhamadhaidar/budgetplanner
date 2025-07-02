package com.haidar.budgetplanner.service;

import com.haidar.budgetplanner.entity.BudgetItem;
import java.util.List;

public interface BudgetService {
    List<BudgetItem> getAll();
    BudgetItem getById(Long id);
    BudgetItem save(BudgetItem item);
    BudgetItem update(Long id, BudgetItem updated);
    void delete(Long id);

    // 🔽 Tambahan untuk fitur anggaran
    double getTotalAnggaran();
    double getSisaAnggaran();
    void setTotalAnggaran(double nilai);
}
