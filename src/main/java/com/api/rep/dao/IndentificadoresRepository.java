package com.api.rep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.Identificadores;

@Repository
public interface IndentificadoresRepository extends JpaRepository<Identificadores, Integer> {

}
