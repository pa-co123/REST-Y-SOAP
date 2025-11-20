package com.example.demo.Repositories;

import com.example.demo.Models.SesionEntrenamientoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISesionEntrenamientoRepository extends JpaRepository<SesionEntrenamientoModel, Long> {
}