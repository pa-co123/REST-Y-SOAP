package com.example.demo.Controllers;

import com.example.demo.Models.FrecuenciaCardiacaModel;
import com.example.demo.Service.FrecuenciaCardiacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frecuencia-cardiaca")
public class FrecuenciaCardiacaController {

    @Autowired
    private FrecuenciaCardiacaService frecuenciaCardiacaService;

    @GetMapping
    public List<FrecuenciaCardiacaModel> getAllRegistros() {
        return frecuenciaCardiacaService.getAllRegistros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrecuenciaCardiacaModel> getRegistroById(@PathVariable Long id) {
        return frecuenciaCardiacaService.getRegistroById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FrecuenciaCardiacaModel createRegistro(@RequestBody FrecuenciaCardiacaModel registro) {
        return frecuenciaCardiacaService.createRegistro(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FrecuenciaCardiacaModel> updateRegistro(@PathVariable Long id, @RequestBody FrecuenciaCardiacaModel registroDetails) {
        return frecuenciaCardiacaService.updateRegistro(id, registroDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        if (frecuenciaCardiacaService.deleteRegistro(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}