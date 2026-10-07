package com.rayfo.pelanggan.entity;

import jakarta.persistence.*;

@Entity
public class Pelanggan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String alamat;
    private String email;
    private String noHp;

    public Pelanggan() {}

    public Pelanggan(Long id, String nama, String alamat, String email, String noHp) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.noHp = noHp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNoHp() { return noHp; }
    public void setNoHp(String noHp) { this.noHp = noHp; }
}