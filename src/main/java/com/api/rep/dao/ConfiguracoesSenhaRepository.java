package com.api.rep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.ConfiguracoesSenha;
import com.api.rep.entity.Rep;

@Repository
public interface ConfiguracoesSenhaRepository extends JpaRepository<ConfiguracoesSenha, Integer> {

	
}
