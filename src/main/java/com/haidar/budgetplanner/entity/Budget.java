package com.haidar.budgetplanner.entity;

import jakarta.persistence.*;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalAnggaran;
    private double sisaAnggaran;

    public Budget() {}

    public Budget(double totalAnggaran) {
        this.totalAnggaran = totalAnggaran;
        this.sisaAnggaran = totalAnggaran;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getTotalAnggaran() { return totalAnggaran; }
    public void setTotalAnggaran(double totalAnggaran) { this.totalAnggaran = totalAnggaran; }

    public double getSisaAnggaran() { return sisaAnggaran; }
    public void setSisaAnggaran(double sisaAnggaran) { this.sisaAnggaran = sisaAnggaran; }
}