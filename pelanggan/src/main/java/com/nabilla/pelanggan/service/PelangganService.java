package com.nabilla.pelanggan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nabilla.pelanggan.entity.Pelanggan;
import com.nabilla.pelanggan.repository.PelangganRepository;

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

    public boolean deletePelanggan(Long id) {
        if (!pelangganRepository.existsById(id)) {
            return false;
        }
        pelangganRepository.deleteById(id);
        return true;
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