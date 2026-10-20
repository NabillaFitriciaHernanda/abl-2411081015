package com.nabilla.order.vo;

import java.util.Date;

public class OrderVO {
    private Long id;
    private Long produkId;
    private Long pelangganId;
    private Date tglTrans;
    private Integer jumlah;
    private Double total;

    private PelangganVO pelanggan;
    private ProdukVO produk;
    
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
    public Date getTglTrans() {
        return tglTrans;
    }
    public void setTglTrans(Date tglTrans) {
        this.tglTrans = tglTrans;
    }
    public Integer getJumlah() {
        return jumlah;
    }
    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public PelangganVO getPelanggan() {
        return pelanggan;
    }
    public void setPelanggan(PelangganVO pelanggan) {
        this.pelanggan = pelanggan;
    }
    public ProdukVO getProduk() {
        return produk;
    }
    public void setProduk(ProdukVO produk) {
        this.produk = produk;
    }

}