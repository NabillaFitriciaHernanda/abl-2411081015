package com.rayfo.pelanggan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rayfo.pelanggan.entity.Pelanggan;
import com.rayfo.pelanggan.repository.PelangganRepository;

@Service
public class PelangganService {
    @Autowired
    private PelangganRepository pelangganRepository;

    public List<Pelanggan> getAllPelanggan() {
        return pelangganRepository.findAll();
    }

    public Pelanggan getPelangganById(Long id) {
        return pelangganRepository.findById(id).orElse(null);
    }

    public Pelanggan savePelanggan(Pelanggan pelanggan) {
        return pelangganRepository.save(pelanggan);
    }

    public void deletePelanggan(Long id) {
        pelangganRepository.deleteById(id);
    }

    public Pelanggan updatePelanggan(Long id, Pelanggan p) {
        Pelanggan existing = pelangganRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setNama(p.getNama());
            existing.setAlamat(p.getAlamat());
            existing.setEmail(p.getEmail());
            existing.setNoHp(p.getNoHp());
            return pelangganRepository.save(existing);
        }
        return null;
    }

    public List<Pelanggan> cariByNama(String nama) {
        return pelangganRepository.findByNamaContainingIgnoreCase(nama);
    }
}