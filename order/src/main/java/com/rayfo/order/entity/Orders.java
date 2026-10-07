package com.rayfo.order.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long produkId;
    private Long pelangganId;
    private LocalDate tglTrans;
    private int jumlah;
    private double total;

    public Orders() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProdukId() { return produkId; }
    public void setProdukId(Long produkId) { this.produkId = produkId; }
    public Long getPelangganId() { return pelangganId; }
    public void setPelangganId(Long pelangganId) { this.pelangganId = pelangganId; }
    public LocalDate getTglTrans() { return tglTrans; }
    public void setTglTrans(LocalDate tglTrans) { this.tglTrans = tglTrans; }
    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}