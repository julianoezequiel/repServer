package com.api.rep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.Configuracao;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Integer> {

}
