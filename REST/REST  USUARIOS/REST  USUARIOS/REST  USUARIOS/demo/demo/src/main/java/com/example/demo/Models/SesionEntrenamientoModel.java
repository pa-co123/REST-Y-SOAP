package com.example.demo.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sesiones_de_entrenamiento")
public class SesionEntrenamientoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Long idSesion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuariosModel usuario;

    @Column(name = "tipo_entrenamiento", nullable = false, length = 50)
    private String tipoEntrenamiento;

    @Column(name = "duracion_min", nullable = false)
    private Integer duracionMin;

    @Column(name = "calorias_quemadas")
    private Integer caloriasQuemadas;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    // Constructores
    public SesionEntrenamientoModel() {
    }

    public SesionEntrenamientoModel(UsuariosModel usuario, String tipoEntrenamiento, Integer duracionMin,
            Integer caloriasQuemadas, LocalDateTime fechaInicio, LocalDateTime fechaFin, String notas) {
        this.usuario = usuario;
        this.tipoEntrenamiento = tipoEntrenamiento;
        this.duracionMin = duracionMin;
        this.caloriasQuemadas = caloriasQuemadas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.notas = notas;
    }

    // Getters y Setters
    public Long getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Long idSesion) {
        this.idSesion = idSesion;
    }

    public UsuariosModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosModel usuario) {
        this.usuario = usuario;
    }

    public String getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public void setTipoEntrenamiento(String tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    public Integer getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(Integer duracionMin) {
        this.duracionMin = duracionMin;
    }

    public Integer getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    public void setCaloriasQuemadas(Integer caloriasQuemadas) {
        this.caloriasQuemadas = caloriasQuemadas;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}