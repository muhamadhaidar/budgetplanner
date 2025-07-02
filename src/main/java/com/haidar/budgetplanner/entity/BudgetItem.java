package com.haidar.budgetplanner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class BudgetItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String kategori;

    @PositiveOrZero
    private double jumlah;

    private String keterangan;

    public BudgetItem() {}

    public BudgetItem(String kategori, double jumlah, String keterangan) {
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public double getJumlah() { return jumlah; }
    public void setJumlah(double jumlah) { this.jumlah = jumlah; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
}
