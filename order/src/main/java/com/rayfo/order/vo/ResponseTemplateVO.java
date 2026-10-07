package com.rayfo.order.vo;

import com.rayfo.order.entity.Orders;

public class ResponseTemplateVO {
    private Orders order;
    private ProdukVO produk;
    private PelangganVO pelanggan;

    public ResponseTemplateVO() {}

    public ResponseTemplateVO(Orders order, ProdukVO produk, PelangganVO pelanggan) {
        this.order = order;
        this.produk = produk;
        this.pelanggan = pelanggan;
    }

    public Orders getOrder() { return order; }
    public void setOrder(Orders order) { this.order = order; }
    public ProdukVO getProduk() { return produk; }
    public void setProduk(ProdukVO produk) { this.produk = produk; }
    public PelangganVO getPelanggan() { return pelanggan; }
    public void setPelanggan(PelangganVO pelanggan) { this.pelanggan = pelanggan; }
}