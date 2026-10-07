package com.nabilla.order.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdukVO {
    private Long id;
    private String nama;
    private Double harga;
    private String deskripsi;
    private Long idjenis;
    private JenisProdukVO jenisProduk;

    public ProdukVO() {}

    public ProdukVO(Long id, String nama, Double harga, String deskripsi, Long idjenis) {
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

    public JenisProdukVO getJenisProduk() {
        return jenisProduk;
    }

    public void setJenisProduk(JenisProdukVO jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    @Override
    public String toString() {
        return "ProdukVO{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                ", deskripsi='" + deskripsi + '\'' +
                ", idjenis=" + idjenis +
                ", jenisProduk=" + jenisProduk +
                '}';
    }
}