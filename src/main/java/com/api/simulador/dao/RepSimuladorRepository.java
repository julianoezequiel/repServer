package com.api.simulador.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.simulador.entity.RepSimulador;

@Repository
public interface RepSimuladorRepository extends JpaRepository<RepSimulador, Integer> {

}
