package com.api.rep.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.Nsr;
import com.api.rep.entity.Rep;

@Repository
public interface NsrRepository extends JpaRepository<Nsr, Integer> {

	@Query(value = "select n from Nsr n where n.repId =:prep")
	public List<Nsr> buscarPorRep(@Param("prep") Rep rep, Pageable pageable);

	default List<Nsr> buscarUltimo(Rep rep) {
		return buscarPorRep(rep, new PageRequest(0, 1));
	}

	@Query(value = "select * from Nsr order by numero_nsr desc LIMIT 1", nativeQuery = true)
	public Nsr findLast();

	@Query(value = "select n from Nsr n where n.numeroNsr = :numNsr")
	public Nsr buscarPorNumNsr(@Param("numNsr") Integer numNsr);

	@Query(value = "select n from Nsr n where n.repId = :prep")
	public List<Nsr> buscarPorRep(@Param("prep") Rep rep);

	// @Query(value = "delete from Nsr where rep_id = :id", nativeQuery = true)
	@Transactional
	public long removeByrepId(Rep rep);

	// @Query(value = "select count(n) from Nsr n where n.repId = :rep")
	// public Long total(@Param("rep") Rep rep);

}
