package com.rayfo.pelanggan.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rayfo.pelanggan.entity.Pelanggan;
import com.rayfo.pelanggan.service.PelangganService;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {
    @Autowired
    private PelangganService pelangganService;

    @GetMapping
    public List<Pelanggan> getAll(@RequestParam(value = "nama", required = false) String nama) {
        if (nama != null) {
            return pelangganService.cariByNama(nama);
        }
        return pelangganService.getAllPelanggan();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelanggan> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pelangganService.getPelangganById(id));
    }

    @PostMapping
    public ResponseEntity<Pelanggan> create(@RequestBody Pelanggan pelanggan) {
        return ResponseEntity.ok(pelangganService.savePelanggan(pelanggan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelanggan> update(@PathVariable("id") Long id, @RequestBody Pelanggan pelanggan) {
        return ResponseEntity.ok(pelangganService.updatePelanggan(id, pelanggan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        pelangganService.deletePelanggan(id);
        return ResponseEntity.noContent().build();
    }
}