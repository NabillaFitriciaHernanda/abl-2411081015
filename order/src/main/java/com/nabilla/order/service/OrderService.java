package com.nabilla.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.nabilla.order.entity.Orders;
import com.nabilla.order.repository.OrderRepository;
import com.nabilla.order.vo.OrderVO;
import com.nabilla.order.vo.PelangganVO;
import com.nabilla.order.vo.ProdukVO;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final RestClient restClient;

    public OrderService(RestClient restClient) {
        this.restClient = restClient;
    }
 
    public List<OrderVO> getAllOrder() {
        return orderRepository.findAll()
            .stream()
            .map(this::getOrderVO)
            .collect(java.util.stream.Collectors.toList());
    }


    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderVO saveOrder(Orders order) {

    // Cek Pelanggan
    PelangganVO pelanggan = getPelanggan(order.getPelanggan_id());

    if (pelanggan == null) {
        throw new RuntimeException("Pelanggan tidak ditemukan");
    }

    // Cek Produk
    ProdukVO produk = getProduk(order.getProduk_id());

    if (produk == null) {
        throw new RuntimeException("Produk tidak ditemukan");
    }

    // Ambil harga Produk dan Menghitung total
    double total = produk.getHarga() * order.getJumlah();

    order.setTotal(total);

    Orders savedOrder = orderRepository.save(order);

    // Simpan Order
    return getOrderVO(savedOrder);
}

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Orders updateOrder(Long id, Orders orders) {
        Orders existing = orderRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setProduk_id(orders.getProduk_id());
            existing.setPelanggan_id(orders.getPelanggan_id());
            existing.setTgl_trans(orders.getTgl_trans());
            existing.setJumlah(orders.getJumlah());
            existing.setTotal(orders.getTotal());
            return orderRepository.save(existing);
        }
        return null;
    }

    // Mengambil Data Pelanggan

    public PelangganVO getPelanggan(Long id) {

        return restClient.get()
                .uri("http://localhost:8082/api/pelanggan/" + id)
                .retrieve()
                .body(PelangganVO.class);
    }

    // Mengambil Data Produk

    public ProdukVO getProduk(Long id) {

        return restClient.get()
                .uri("http://localhost:8081/api/produk/" + id)
                .retrieve()
                .body(ProdukVO.class);
    }

    public OrderVO getOrderVO(Orders order) {

        PelangganVO pelanggan = getPelanggan(order.getPelanggan_id());
        ProdukVO produk = getProduk(order.getProduk_id());

        OrderVO orderVO = new OrderVO();

        orderVO.setId(order.getId());
        orderVO.setProdukId(order.getProduk_id());
        orderVO.setPelangganId(order.getPelanggan_id());
        orderVO.setJumlah(order.getJumlah());
        orderVO.setTglTrans(order.getTgl_trans());
        orderVO.setTotal(order.getTotal());

        orderVO.setPelanggan(pelanggan);
        orderVO.setProduk(produk);

        return orderVO;
    }

}