package com.example.demo.Controllers;

import com.example.demo.Models.SesionEntrenamientoModel;
import com.example.demo.Service.SesionEntrenamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
public class SesionEntrenamientoController {

    @Autowired
    private SesionEntrenamientoService sesionEntrenamientoService;

    @GetMapping
    public List<SesionEntrenamientoModel> getAllSesiones() {
        return sesionEntrenamientoService.getAllSesiones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionEntrenamientoModel> getSesionById(@PathVariable Long id) {
        return sesionEntrenamientoService.getSesionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SesionEntrenamientoModel createSesion(@RequestBody SesionEntrenamientoModel sesion) {
        return sesionEntrenamientoService.createSesion(sesion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionEntrenamientoModel> updateSesion(@PathVariable Long id, @RequestBody SesionEntrenamientoModel sesionDetails) {
        return sesionEntrenamientoService.updateSesion(id, sesionDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesion(@PathVariable Long id) {
        if (sesionEntrenamientoService.deleteSesion(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}