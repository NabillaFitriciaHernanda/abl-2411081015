package com.nabilla.order.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nabilla.order.entity.Orders;
import com.nabilla.order.repository.OrderRepository;
import com.nabilla.order.vo.JenisProdukVO;
import com.nabilla.order.vo.PelangganVO;
import com.nabilla.order.vo.ProdukVO;
import com.nabilla.order.vo.ResponseTemplateVO;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    // URL langsung ke microservice Produk (Port 8080) dan Pelanggan (Port 8081) tanpa Eureka
    private static final String PRODUK_URL = "http://localhost:8080/api/produk/";
    private static final String PRODUK_JENIS_URL = "http://localhost:8080/api/produk/jenis/";
    private static final String PELANGGAN_URL = "http://localhost:8081/api/pelanggan/";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Mengambil data Produk dari microservice Produk via RestTemplate
     */
    public ProdukVO getProduk(Long produkId) {
        try {
            ProdukVO produk = restTemplate.getForObject(PRODUK_URL + produkId, ProdukVO.class);
            if (produk != null && produk.getIdjenis() != null) {
                try {
                    JenisProdukVO jenis = restTemplate.getForObject(PRODUK_JENIS_URL + produk.getIdjenis(), JenisProdukVO.class);
                    produk.setJenisProduk(jenis);
                } catch (Exception e) {
                    logger.warn("Gagal mengambil JenisProduk dengan id {}: {}", produk.getIdjenis(), e.getMessage());
                }
            }
            return produk;
        } catch (Exception e) {
            logger.error("Gagal mengambil data Produk dengan id {}: {}", produkId, e.getMessage());
            return null;
        }
    }

    /**
     * Mengambil data Pelanggan dari microservice Pelanggan via RestTemplate
     */
    public PelangganVO getPelanggan(Long pelangganId) {
        try {
            return restTemplate.getForObject(PELANGGAN_URL + pelangganId, PelangganVO.class);
        } catch (Exception e) {
            logger.error("Gagal mengambil data Pelanggan dengan id {}: {}", pelangganId, e.getMessage());
            return null;
        }
    }

    /**
     * Mengambil satu data Order lengkap dengan detail Produk dan Pelanggan (ResponseTemplateVO)
     */
    public ResponseTemplateVO getOrderWithDetail(Long orderId) {
        Orders order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return null;
        }
        ProdukVO produk = getProduk(order.getProdukId());
        PelangganVO pelanggan = getPelanggan(order.getPelangganId());
        return new ResponseTemplateVO(order, produk, pelanggan);
    }

    /**
     * Mengambil seluruh data Order beserta detail Produk dan Pelanggan
     */
    public List<ResponseTemplateVO> getAllOrdersWithDetail() {
        List<Orders> orders = orderRepository.findAll();
        List<ResponseTemplateVO> listResponse = new ArrayList<>();
        for (Orders order : orders) {
            ProdukVO produk = getProduk(order.getProdukId());
            PelangganVO pelanggan = getPelanggan(order.getPelangganId());
            listResponse.add(new ResponseTemplateVO(order, produk, pelanggan));
        }
        return listResponse;
    }

    /**
     * Mengambil data Order berdasarkan pelangganId dengan detail VO
     */
    public List<ResponseTemplateVO> getOrdersByPelangganIdWithDetail(Long pelangganId) {
        List<Orders> orders = orderRepository.findByPelangganId(pelangganId);
        List<ResponseTemplateVO> listResponse = new ArrayList<>();
        for (Orders order : orders) {
            ProdukVO produk = getProduk(order.getProdukId());
            PelangganVO pelanggan = getPelanggan(order.getPelangganId());
            listResponse.add(new ResponseTemplateVO(order, produk, pelanggan));
        }
        return listResponse;
    }

    /**
     * Membuat order baru:
     * - Validasi keberadaan pelanggan ke microservice pelanggan
     * - Validasi keberadaan produk ke microservice produk
     * - Hitung total otomatis (harga produk * jumlah)
     */
    public Orders saveOrder(Orders order) {
        PelangganVO pelanggan = getPelanggan(order.getPelangganId());
        if (pelanggan == null || pelanggan.getId() == null) {
            throw new IllegalArgumentException("Pelanggan dengan ID " + order.getPelangganId() + " tidak ditemukan!");
        }

        ProdukVO produk = getProduk(order.getProdukId());
        if (produk == null || produk.getId() == null) {
            throw new IllegalArgumentException("Produk dengan ID " + order.getProdukId() + " tidak ditemukan!");
        }

        if (order.getTglTrans() == null) {
            order.setTglTrans(LocalDate.now());
        }

        // Kalkulasi total bayar
        order.setTotal(produk.getHarga() * order.getJumlah());

        return orderRepository.save(order);
    }

    /**
     * Mengambil entitas Orders sederhana
     */
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    /**
     * Mengupdate data order
     */
    public Orders updateOrder(Long id, Orders order) {
        Orders existing = orderRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        PelangganVO pelanggan = getPelanggan(order.getPelangganId());
        if (pelanggan == null || pelanggan.getId() == null) {
            throw new IllegalArgumentException("Pelanggan dengan ID " + order.getPelangganId() + " tidak ditemukan!");
        }

        ProdukVO produk = getProduk(order.getProdukId());
        if (produk == null || produk.getId() == null) {
            throw new IllegalArgumentException("Produk dengan ID " + order.getProdukId() + " tidak ditemukan!");
        }

        existing.setProdukId(order.getProdukId());
        existing.setPelangganId(order.getPelangganId());
        existing.setJumlah(order.getJumlah());
        if (order.getTglTrans() != null) {
            existing.setTglTrans(order.getTglTrans());
        }
        existing.setTotal(produk.getHarga() * order.getJumlah());

        return orderRepository.save(existing);
    }

    /**
     * Menghapus order
     */
    public boolean deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return false;
        }
        orderRepository.deleteById(id);
        return true;
    }
}