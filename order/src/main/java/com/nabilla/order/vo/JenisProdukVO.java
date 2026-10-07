package com.nabilla.order.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JenisProdukVO {
    private Long id;
    private String jenis;

    public JenisProdukVO() {}

    public JenisProdukVO(Long id, String jenis) {
        this.id = id;
        this.jenis = jenis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @Override
    public String toString() {
        return "JenisProdukVO{" +
                "id=" + id +
                ", jenis='" + jenis + '\'' +
                '}';
    }
}