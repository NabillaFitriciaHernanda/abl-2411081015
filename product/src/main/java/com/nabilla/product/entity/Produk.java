package com.nabilla.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produk")
public class Produk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private Double harga;
    private String deskripsi;
    private Long idjenis;

    public Produk() {}

    public Produk(Long id, String nama, Double harga, String deskripsi, Long idjenis) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.idjenis = idjenis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getHarga() {
        return harga != null ? harga : 0.0;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Long getIdjenis() {
        return idjenis;
    }

    public void setIdjenis(Long idjenis) {
        this.idjenis = idjenis;
    }

    @Override
    public String toString() {
        return "Produk{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                ", deskripsi='" + deskripsi + '\'' +
                ", idjenis=" + idjenis +
                '}';
    }
}