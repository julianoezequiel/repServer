package com.api.rep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.Tarefa;
import com.api.rep.entity.Rep;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

	/*@Query(value = "select p from Pendencia p where p.repId =:prep")
	public List<Pendencia> buscarPorRep(@Param("prep") Rep rep, Pageable pageable);*/

	/*default List<Pendencia> buscarPorRep(Rep rep) {
		return buscarPorRep(rep, new PageRequest(0, 1));
	}*/
	
	@Query(value = "select p from Tarefa p where p.repId = :prep")
	public List<Tarefa> buscarPorRep(@Param("prep") Rep rep);
	
	@Query(value = "select p from Tarefa p where p.nsu = :pnsu")
	public List<Tarefa> buscarPorNsu(@Param("pnsu") int nsu);

}
