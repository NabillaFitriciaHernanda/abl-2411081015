package com.rayfo.order.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.rayfo.order.vo.JenisProdukVO;
import com.rayfo.order.vo.PelangganVO;
import com.rayfo.order.vo.ProdukVO;

@Service
public class ClientService {
    private static final String URL_PRODUK = "http://localhost:8080/api/produk";
    private static final String URL_PELANGGAN = "http://localhost:8081/api/pelanggan";

    private final RestClient produkClient = RestClient.create(URL_PRODUK);
    private final RestClient pelangganClient = RestClient.create(URL_PELANGGAN);

    public ProdukVO getProduk(Long id) {
        try {
            return produkClient.get().uri("/{id}", id)
                    .retrieve().body(ProdukVO.class);
        } catch (Exception e) {
            System.out.println("ERROR getProduk: " + e);
            return null;
        }
    }

    public JenisProdukVO getJenisProduk(Long id) {
        try {
            return produkClient.get().uri("/jenis/{id}", id)
                    .retrieve().body(JenisProdukVO.class);
        } catch (Exception e) {
            System.out.println("ERROR getJenisProduk: " + e);
            return null;
        }
    }

    public PelangganVO getPelanggan(Long id) {
        try {
            return pelangganClient.get().uri("/{id}", id)
                    .retrieve().body(PelangganVO.class);
        } catch (Exception e) {
            System.out.println("ERROR getPelanggan: " + e);
            return null;
        }
    }
}