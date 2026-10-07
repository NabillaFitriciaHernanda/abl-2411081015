package com.nabilla.product.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nabilla.product.entity.JenisProduk;
import com.nabilla.product.entity.Produk;
import com.nabilla.product.service.JenisProdukService;
import com.nabilla.product.service.ProdukService;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {

    @Autowired
    private ProdukService produkService;

    @Autowired
    private JenisProdukService jenisProdukService;

    // --- ENDPOINT PRODUK ---

    @GetMapping
    public List<Produk> getAllProduk(@RequestParam(value = "idjenis", required = false) Long idjenis) {
        if (idjenis != null) {
            return produkService.getAllBarangByIdJenis(idjenis);
        }
        return produkService.getAllProduk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProdukById(@PathVariable("id") Long id) {
        Produk produk = produkService.getProdukById(id);
        if (produk == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produk);
    }

    @PostMapping
    public ResponseEntity<Produk> createProduk(@RequestBody Produk produk) {
        return new ResponseEntity<>(produkService.saveProduk(produk), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produk> updateProduk(@PathVariable("id") Long id, @RequestBody Produk produk) {
        Produk updated = produkService.updateProduk(id, produk);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduk(@PathVariable("id") Long id) {
        boolean isDeleted = produkService.deleteProduk(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // --- ENDPOINT JENIS PRODUK ---

    @GetMapping("/jenis")
    public List<JenisProduk> getAllJenisProduk() {
        return jenisProdukService.getAllJenisProduk();
    }

    @GetMapping("/jenis/{id}")
    public ResponseEntity<JenisProduk> getJenisProdukById(@PathVariable("id") Long id) {
        JenisProduk jenisProduk = jenisProdukService.getJenisProdukById(id);
        if (jenisProduk == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jenisProduk);
    }

    @PostMapping("/jenis")
    public ResponseEntity<JenisProduk> createJenisProduk(@RequestBody JenisProduk jenisProduk) {
        return new ResponseEntity<>(jenisProdukService.saveJenisProduk(jenisProduk), HttpStatus.CREATED);
    }

    @PutMapping("/jenis/{id}")
    public ResponseEntity<JenisProduk> updateJenisProduk(
            @PathVariable("id") Long id, @RequestBody JenisProduk jenisProduk) {
        JenisProduk updated = jenisProdukService.updateJenisProduk(id, jenisProduk);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/jenis/{id}")
    public ResponseEntity<Void> deleteJenisProduk(@PathVariable("id") Long id) {
        boolean isDeleted = jenisProdukService.deleteJenisProduk(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}