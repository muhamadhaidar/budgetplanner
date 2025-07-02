package com.haidar.budgetplanner.controller;

import com.haidar.budgetplanner.entity.BudgetItem;
import com.haidar.budgetplanner.repository.BudgetItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BudgetItemController {

    @Autowired
    private BudgetItemRepository budgetItemRepository;

    private double totalAnggaran = 1000000; // default anggaran awal

    @GetMapping("/pengeluaran")
    public String formPengeluaran(Model model) {
        model.addAttribute("budgetItem", new BudgetItem());
        return "form";
    }

    @PostMapping("/pengeluaran")
    public String submitPengeluaran(@ModelAttribute BudgetItem budgetItem) {
        budgetItemRepository.save(budgetItem);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String tampilkanList(Model model) {
        List<BudgetItem> items = budgetItemRepository.findAll();
        double totalPengeluaran = items.stream().mapToDouble(BudgetItem::getJumlah).sum();
        double sisaAnggaran = totalAnggaran - totalPengeluaran;

        model.addAttribute("items", items);
        model.addAttribute("totalAnggaran", totalAnggaran);
        model.addAttribute("sisaAnggaran", sisaAnggaran);
        return "list";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<BudgetItem> items = budgetItemRepository.findAll();
        double totalPengeluaran = items.stream().mapToDouble(BudgetItem::getJumlah).sum();
        double sisaAnggaran = totalAnggaran - totalPengeluaran;

        Map<String, Double> budget = new HashMap<>();
        budget.put("totalAnggaran", totalAnggaran);
        budget.put("sisaAnggaran", sisaAnggaran);

        model.addAttribute("items", items);
        model.addAttribute("budget", budget);
        return "index";
    }

    @GetMapping("/atur-anggaran")
    public String formAturAnggaran() {
    return "atur-anggaran";
    }

    @PostMapping("/atur-anggaran")
    public String simpanAnggaran(@RequestParam("nominal") double nominal) {
    this.totalAnggaran = nominal;
    return "redirect:/";
    }
}
