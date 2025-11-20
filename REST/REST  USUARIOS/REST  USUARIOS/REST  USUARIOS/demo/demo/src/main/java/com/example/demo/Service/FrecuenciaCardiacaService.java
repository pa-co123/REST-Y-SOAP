package com.example.demo.Service;

import com.example.demo.Models.FrecuenciaCardiacaModel;
import com.example.demo.Repositories.FrecuenciaCardiacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrecuenciaCardiacaService {

    @Autowired
    private FrecuenciaCardiacaRepository frecuenciaCardiacaRepository;

    public List<FrecuenciaCardiacaModel> getAllRegistros() {
        return frecuenciaCardiacaRepository.findAll();
    }

    public Optional<FrecuenciaCardiacaModel> getRegistroById(Long id) {
        return frecuenciaCardiacaRepository.findById(id);
    }

    public FrecuenciaCardiacaModel createRegistro(FrecuenciaCardiacaModel registro) {
        // Solución temporal: Duplicar el valor para satisfacer ambas columnas NOT NULL en la BD.
        registro.setHeartRate(registro.getBpm());
        return frecuenciaCardiacaRepository.save(registro);
    }

    public Optional<FrecuenciaCardiacaModel> updateRegistro(Long id, FrecuenciaCardiacaModel registroDetails) {
        return frecuenciaCardiacaRepository.findById(id).map(registro -> {
            registro.setUsuario(registroDetails.getUsuario());
            registro.setDispositivo(registroDetails.getDispositivo()); // This line now works with the corrected model
            registro.setBpm(registroDetails.getBpm());
            registro.setHeartRate(registroDetails.getBpm()); // Duplicar también en la actualización
            registro.setFechaRegistro(registroDetails.getFechaRegistro());
            return frecuenciaCardiacaRepository.save(registro);
        });
    }

    public boolean deleteRegistro(Long id) {
        return frecuenciaCardiacaRepository.findById(id).map(registro -> {
            frecuenciaCardiacaRepository.delete(registro);
            return true;
        }).orElse(false);
    }
}