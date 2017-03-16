package com.api.rep.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.Empregado;
import com.api.rep.entity.Rep;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Integer> {

	@Query(value = "select e from Empregado e where e.empregadoPis = :pis and e.repId = :idRep")
	public Optional<Empregado> buscarPorPis(@Param("pis") String pis, @Param("idRep") Rep idRep);

	@Transactional
	public long removeByrepId(Rep rep);
}
