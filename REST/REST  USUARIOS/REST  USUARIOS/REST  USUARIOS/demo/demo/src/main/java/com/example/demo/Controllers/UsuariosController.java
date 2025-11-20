package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.UsuariosModel;
import com.example.demo.Service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    
    @Autowired
    private UsuariosService usuarioService;

    //Get all usuarios
    @GetMapping()
    public List<UsuariosModel> getallUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    //Get usuario by id
    @GetMapping("/{id}")
    public ResponseEntity<UsuariosModel> getUsuarioById(@PathVariable Long id){
        Optional<UsuariosModel> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Create usuario
    @PostMapping
    public UsuariosModel createUsuario(@RequestBody UsuariosModel usuario){
        return usuarioService.saveUsuario(usuario);
    }

    //Update usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuariosModel> updateUsuario(@PathVariable Long id, @RequestBody UsuariosModel usuarioDetails) {
        Optional<UsuariosModel> usuarioOptional = usuarioService.getUsuarioById(id);
        
        if (usuarioOptional.isPresent()) {
            UsuariosModel usuarioToUpdate = usuarioOptional.get();
            
            // Actualiza todos los campos de datos
            usuarioToUpdate.setNombres(usuarioDetails.getNombres());
            usuarioToUpdate.setApellidos(usuarioDetails.getApellidos());
            usuarioToUpdate.setEmail(usuarioDetails.getEmail());
            usuarioToUpdate.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
            usuarioToUpdate.setGenero(usuarioDetails.getGenero());
            usuarioToUpdate.setPeso(usuarioDetails.getPeso());
            usuarioToUpdate.setAltura(usuarioDetails.getAltura());
            
            
            UsuariosModel updatedUsuario = usuarioService.saveUsuario(usuarioToUpdate);
            return ResponseEntity.ok(updatedUsuario); 

        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    //Delete usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        Optional<UsuariosModel> usuario = usuarioService.getUsuarioById(id);
        if (usuario.isPresent()) {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        
}
