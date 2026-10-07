package com.nabilla.order.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long produkId;
    private Long pelangganId;
    private LocalDate tglTrans;
    private Integer jumlah;
    private Double total;

    public Orders() {}

    public Orders(Long id, Long produkId, Long pelangganId, LocalDate tglTrans, Integer jumlah, Double total) {
        this.id = id;
        this.produkId = produkId;
        this.pelangganId = pelangganId;
        this.tglTrans = tglTrans;
        this.jumlah = jumlah;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdukId() {
        return produkId;
    }

    public void setProdukId(Long produkId) {
        this.produkId = produkId;
    }

    public Long getPelangganId() {
        return pelangganId;
    }

    public void setPelangganId(Long pelangganId) {
        this.pelangganId = pelangganId;
    }

    public LocalDate getTglTrans() {
        return tglTrans;
    }

    public void setTglTrans(LocalDate tglTrans) {
        this.tglTrans = tglTrans;
    }

    public Integer getJumlah() {
        return jumlah != null ? jumlah : 0;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Double getTotal() {
        return total != null ? total : 0.0;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", produkId=" + produkId +
                ", pelangganId=" + pelangganId +
                ", tglTrans=" + tglTrans +
                ", jumlah=" + jumlah +
                ", total=" + total +
                '}';
    }
}