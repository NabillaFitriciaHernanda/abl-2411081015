package com.rayfo.order.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rayfo.order.entity.Orders;
import com.rayfo.order.repository.OrderRepository;
import com.rayfo.order.vo.PelangganVO;
import com.rayfo.order.vo.ProdukVO;
import com.rayfo.order.vo.ResponseTemplateVO;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private ClientService clientService;

    // ---------- CRUD ----------
    public Orders buatOrder(Orders o) {
        ProdukVO produk = validasi(o);
        if (o.getTglTrans() == null) {
            o.setTglTrans(LocalDate.now());
        }
        o.setTotal(produk.getHarga() * o.getJumlah());
        return orderRepository.save(o);
    }

    public List<Orders> getAll() {
        return orderRepository.findAll();
    }

    public Orders getById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Orders> getByPelanggan(Long id) {
        return orderRepository.findByPelangganId(id);
    }

    public Orders updateOrder(Long id, Orders o) {
        Orders existing = orderRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        ProdukVO produk = validasi(o);
        existing.setProdukId(o.getProdukId());
        existing.setPelangganId(o.getPelangganId());
        existing.setJumlah(o.getJumlah());
        if (o.getTglTrans() != null) {
            existing.setTglTrans(o.getTglTrans());
        }
        existing.setTotal(produk.getHarga() * o.getJumlah());
        return orderRepository.save(existing);
    }

    public boolean deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return false;
        }
        orderRepository.deleteById(id);
        return true;
    }

    // ---------- Gabungan order + produk + pelanggan (pakai VO) ----------
    public ResponseTemplateVO getDetail(Long id) {
        Orders order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        return susunDetail(order);
    }

    public List<ResponseTemplateVO> getAllDetail() {
        List<ResponseTemplateVO> hasil = new ArrayList<>();
        for (Orders order : orderRepository.findAll()) {
            hasil.add(susunDetail(order));
        }
        return hasil;
    }

    public List<ResponseTemplateVO> getDetailByPelanggan(Long pelangganId) {
        List<ResponseTemplateVO> hasil = new ArrayList<>();
        for (Orders order : orderRepository.findByPelangganId(pelangganId)) {
            hasil.add(susunDetail(order));
        }
        return hasil;
    }

    // ---------- Method bantu ----------
    private ResponseTemplateVO susunDetail(Orders order) {
        ProdukVO produk = clientService.getProduk(order.getProdukId());
        if (produk != null && produk.getIdJenis() != null) {
            produk.setJenisProduk(clientService.getJenisProduk(produk.getIdJenis()));
        }
        PelangganVO pelanggan = clientService.getPelanggan(order.getPelangganId());
        return new ResponseTemplateVO(order, produk, pelanggan);
    }

    // Cek pelanggan & produk ke service lain, kembalikan data produk
    private ProdukVO validasi(Orders o) {
        PelangganVO pelanggan = clientService.getPelanggan(o.getPelangganId());
        if (pelanggan == null || pelanggan.getId() == null) {
            throw new IllegalArgumentException("Pelanggan tidak ditemukan");
        }
        ProdukVO produk = clientService.getProduk(o.getProdukId());
        if (produk == null || produk.getId() == null) {
            throw new IllegalArgumentException("Produk tidak ditemukan");
        }
        return produk;
    }
}