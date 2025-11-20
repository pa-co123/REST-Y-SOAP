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
@Table(name = "registro_frecuencia_cardiaca")
public class FrecuenciaCardiacaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fc")
    private Long idFc;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuariosModel usuario;

    @ManyToOne
    @JoinColumn(name = "id_dispositivo", nullable = false)
    private DispositivoIoTModel dispositivo;

    @Column(name = "bpm", nullable = false)
    private Integer bpm;

    @Column(name = "heart_rate", nullable = false)
    private Integer heartRate;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    // Constructores
    public FrecuenciaCardiacaModel() {
    }

    public FrecuenciaCardiacaModel(UsuariosModel usuario, DispositivoIoTModel dispositivo, Integer bpm, Integer heartRate,
            LocalDateTime fechaRegistro) {
        this.usuario = usuario;
        this.dispositivo = dispositivo;
        this.bpm = bpm;
        this.heartRate = heartRate;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public Long getIdFc() {
        return idFc;
    }

    public void setIdFc(Long idFc) {
        this.idFc = idFc;
    }

    public UsuariosModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosModel usuario) {
        this.usuario = usuario;
    }

    public DispositivoIoTModel getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(DispositivoIoTModel dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}