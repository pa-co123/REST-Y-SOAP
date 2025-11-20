package com.example.demo.Repositories;

import com.example.demo.Models.FrecuenciaCardiacaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrecuenciaCardiacaRepository extends JpaRepository<FrecuenciaCardiacaModel, Long> {
}