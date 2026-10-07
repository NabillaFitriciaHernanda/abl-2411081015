package com.nabilla.pelanggan.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nabilla.pelanggan.entity.Pelanggan;
import com.nabilla.pelanggan.service.PelangganService;

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
        Pelanggan pelanggan = pelangganService.getPelangganById(id);
        if (pelanggan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pelanggan);
    }

    @PostMapping
    public ResponseEntity<Pelanggan> create(@RequestBody Pelanggan pelanggan) {
        return new ResponseEntity<>(pelangganService.savePelanggan(pelanggan), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelanggan> update(@PathVariable("id") Long id, @RequestBody Pelanggan pelanggan) {
        Pelanggan updated = pelangganService.updatePelanggan(id, pelanggan);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boolean isDeleted = pelangganService.deletePelanggan(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}