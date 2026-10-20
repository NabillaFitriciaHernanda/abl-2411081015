package com.nabilla.order.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long produk_id;
    private Long pelanggan_id;
    @JsonProperty("tglTrans")
    private Date tgl_trans;
    private int jumlah;
    private double total;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProduk_id() {
        return produk_id;
    }
    public void setProduk_id(Long produk_id) {
        this.produk_id = produk_id;
    }
    public Long getPelanggan_id() {
        return pelanggan_id;
    }
    public void setPelanggan_id(Long pelanggan_id) {
        this.pelanggan_id = pelanggan_id;
    }
    public Date getTgl_trans() {
        return tgl_trans;
    }
    public void setTgl_trans(Date tgl_trans) {
        this.tgl_trans = tgl_trans;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

}