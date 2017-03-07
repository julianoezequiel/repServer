package com.api.rep.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.Empregador;
import com.api.rep.entity.Rep;

@Repository
public interface EmpregadorRepository extends JpaRepository<Empregador, Integer> {

	@Query(value = "Select e from Empregador e where e.empregadorIdent = :indentificador")
	public Optional<Empregador> buscarPorIndentificador(@Param("indentificador") String indentificador);
	
	
}
