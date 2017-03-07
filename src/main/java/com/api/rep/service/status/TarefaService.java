package com.api.rep.service.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rep.dao.TarefaRepository;
import com.api.rep.entity.Tarefa;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository pendenciaRepository;

	public void excluir(Integer id) {
		this.pendenciaRepository.delete(id);
	}

	public void excluir(Tarefa pendencia) {
		this.pendenciaRepository.delete(pendencia);
	}

}
