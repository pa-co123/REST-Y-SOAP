package com.example.demo.Service;

import com.example.demo.Models.SesionEntrenamientoModel;
import com.example.demo.Repositories.ISesionEntrenamientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SesionEntrenamientoService {

    @Autowired
    private ISesionEntrenamientoRepository isesionEntrenamientoRepository;

    public List<SesionEntrenamientoModel> getAllSesiones() {
        return isesionEntrenamientoRepository.findAll();
    }

    public Optional<SesionEntrenamientoModel> getSesionById(Long id) {
        return isesionEntrenamientoRepository.findById(id);
    }

    public SesionEntrenamientoModel createSesion(SesionEntrenamientoModel sesion) {
        return isesionEntrenamientoRepository.save(sesion);
    }

    public Optional<SesionEntrenamientoModel> updateSesion(Long id, SesionEntrenamientoModel sesionDetails) {
        return isesionEntrenamientoRepository.findById(id).map(sesion -> {
            sesion.setUsuario(sesionDetails.getUsuario());
            sesion.setTipoEntrenamiento(sesionDetails.getTipoEntrenamiento());
            sesion.setDuracionMin(sesionDetails.getDuracionMin());
            sesion.setCaloriasQuemadas(sesionDetails.getCaloriasQuemadas());
            sesion.setFechaInicio(sesionDetails.getFechaInicio());
            sesion.setFechaFin(sesionDetails.getFechaFin());
            sesion.setNotas(sesionDetails.getNotas());
            return isesionEntrenamientoRepository.save(sesion);
        });
    }

    public boolean deleteSesion(Long id) {
        return isesionEntrenamientoRepository.findById(id).map(sesion -> {
            isesionEntrenamientoRepository.delete(sesion);
            return true;
        }).orElse(false);
    }
}