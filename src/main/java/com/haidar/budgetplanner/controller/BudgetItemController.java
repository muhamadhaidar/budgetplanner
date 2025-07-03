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

    private double totalAnggaran = 0; // default anggaran awal

    @GetMapping("/pengeluaran")
    public String formPengeluaran(Model model) {
        model.addAttribute("budgetItem", new BudgetItem());
        return "form";
    }

    @PostMapping("/pengeluaran")
    public String submitPengeluaran(@ModelAttribute BudgetItem budgetItem) {
        budgetItem.setJenis("pengeluaran");
        budgetItemRepository.save(budgetItem);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String tampilkanList(Model model) {
        List<BudgetItem> items = budgetItemRepository.findAll();
        double totalPengeluaran = items.stream().filter(i -> "pengeluaran".equals(i.getJenis())).mapToDouble(BudgetItem::getJumlah).sum();
        double sisaAnggaran = totalAnggaran - totalPengeluaran;

        model.addAttribute("items", items);
        model.addAttribute("totalAnggaran", totalAnggaran);
        model.addAttribute("sisaAnggaran", sisaAnggaran);
        return "list";
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) Boolean tambahAnggaran,
                        @RequestParam(required = false) Double jumlah) {
        List<BudgetItem> items = budgetItemRepository.findAll();
        double totalPengeluaran = items.stream().filter(i -> "pengeluaran".equals(i.getJenis())).mapToDouble(BudgetItem::getJumlah).sum();
        double sisaAnggaran = totalAnggaran - totalPengeluaran;

        Map<String, Double> budget = new HashMap<>();
        budget.put("totalAnggaran", totalAnggaran);
        budget.put("sisaAnggaran", sisaAnggaran);

        model.addAttribute("items", items);
        model.addAttribute("budget", budget);

        if (tambahAnggaran != null && tambahAnggaran && jumlah != null) {
            model.addAttribute("tambahAnggaran", true);
            model.addAttribute("jumlah", jumlah);
        }

        return "index";
    }

    @GetMapping("/atur-anggaran")
    public String formAturAnggaran() {
        return "atur-anggaran";
    }

    @PostMapping("/atur-anggaran")
    public String tambahAnggaran(@RequestParam("nominal") double nominal) {
        this.totalAnggaran += nominal;

        BudgetItem tambahan = new BudgetItem();
        tambahan.setKategori("TAMBAH ANGGARAN");
        tambahan.setJumlah(nominal);
        tambahan.setKeterangan("Penambahan anggaran");
        tambahan.setJenis("tambah-anggaran");
        budgetItemRepository.save(tambahan);

        return "redirect:/?tambahAnggaran=true&jumlah=" + nominal;
    }

    @PostMapping("/hapus-anggaran")
    public String hapusTotalAnggaran() {
        this.totalAnggaran = 0;
        return "redirect:/";
    }

    @PostMapping("/pengeluaran/{id}/hapus")
    public String hapusPengeluaran(@PathVariable Long id) {
        budgetItemRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/pengeluaran/hapus-semua")
    public String hapusSemuaPengeluaran() {
        budgetItemRepository.deleteAll();
        return "redirect:/";
    }
}