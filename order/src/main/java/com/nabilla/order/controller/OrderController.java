package com.nabilla.order.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nabilla.order.entity.Orders;
import com.nabilla.order.service.OrderService;
import com.nabilla.order.vo.ResponseTemplateVO;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Endpoint untuk membuat Order baru
     * POST /api/order
     */
    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody Orders order) {
        try {
            Orders createdOrder = orderService.saveOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Terjadi kesalahan sistem: " + e.getMessage());
        }
    }

    /**
     * Endpoint untuk mengambil semua order lengkap dengan detail VO (Order + Produk + Pelanggan)
     * GET /api/order
     * GET /api/order?pelangganId=1
     */
    @GetMapping
    public ResponseEntity<List<ResponseTemplateVO>> getAllOrders(
            @RequestParam(value = "pelangganId", required = false) Long pelangganId) {
        if (pelangganId != null) {
            return ResponseEntity.ok(orderService.getOrdersByPelangganIdWithDetail(pelangganId));
        }
        return ResponseEntity.ok(orderService.getAllOrdersWithDetail());
    }

    /**
     * Endpoint untuk mengambil satu data order lengkap dengan detail VO
     * GET /api/order/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderWithDetail(@PathVariable("id") Long id) {
        ResponseTemplateVO responseTemplateVO = orderService.getOrderWithDetail(id);
        if (responseTemplateVO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order dengan ID " + id + " tidak ditemukan");
        }
        return ResponseEntity.ok(responseTemplateVO);
    }

    /**
     * Endpoint untuk mengambil satu data order (hanya entity Orders)
     * GET /api/order/{id}/simple
     */
    @GetMapping("/{id}/simple")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
        Orders order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order dengan ID " + id + " tidak ditemukan");
        }
        return ResponseEntity.ok(order);
    }

    /**
     * Endpoint untuk mengupdate order
     * PUT /api/order/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody Orders order) {
        try {
            Orders updatedOrder = orderService.updateOrder(id, order);
            if (updatedOrder == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order dengan ID " + id + " tidak ditemukan");
            }
            return ResponseEntity.ok(updatedOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Terjadi kesalahan sistem: " + e.getMessage());
        }
    }

    /**
     * Endpoint untuk menghapus order
     * DELETE /api/order/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        boolean isDeleted = orderService.deleteOrder(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order dengan ID " + id + " tidak ditemukan");
        }
        return ResponseEntity.ok("Order dengan ID " + id + " berhasil dihapus");
    }
}