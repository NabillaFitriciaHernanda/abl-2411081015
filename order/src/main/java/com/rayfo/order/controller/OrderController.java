package com.rayfo.order.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rayfo.order.entity.Orders;
import com.rayfo.order.service.OrderService;
import com.rayfo.order.vo.ResponseTemplateVO;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Orders order) {
        try {
            return ResponseEntity.ok(orderService.buatOrder(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/order -> langsung lengkap (order + produk + pelanggan)
    @GetMapping
    public List<ResponseTemplateVO> getAll(@RequestParam(value = "pelangganId", required = false) Long pelangganId) {
        if (pelangganId != null) {
            return orderService.getDetailByPelanggan(pelangganId);
        }
        return orderService.getAllDetail();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Orders order = orderService.getById(id);
        if (order == null) {
            return ResponseEntity.status(404).body("Order tidak ditemukan");
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id) {
        ResponseTemplateVO vo = orderService.getDetail(id);
        if (vo == null) {
            return ResponseEntity.status(404).body("Order tidak ditemukan");
        }
        return ResponseEntity.ok(vo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Orders order) {
        try {
            Orders hasil = orderService.updateOrder(id, order);
            if (hasil == null) {
                return ResponseEntity.status(404).body("Order tidak ditemukan");
            }
            return ResponseEntity.ok(hasil);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!orderService.deleteOrder(id)) {
            return ResponseEntity.status(404).body("Order tidak ditemukan");
        }
        return ResponseEntity.noContent().build();
    }
}