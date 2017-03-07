package com.api.rep.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dao.EmpregadoRespository;
import com.api.rep.dao.TarefaRepository;
import com.api.rep.dto.comandos.ComandoAbstract;
import com.api.rep.dto.comunicacao.RespostaRepDTO;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.entity.Tarefa;
import com.api.rep.entity.Rep;
import com.api.rep.service.rep.RepService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService {

	public final static Logger LOGGER = LoggerFactory.getLogger(ApiService.class.getName());

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private RepService repService;

	@Autowired
	private EmpregadoRespository empregadoRespository;

	@Autowired
	private ObjectMapper mapper;

	private Rep rep;

	public TarefaRepository getTarefaRepository() {
		return tarefaRepository;
	}

	public void setTarefaRepository(TarefaRepository tarefaRepository) {
		this.tarefaRepository = tarefaRepository;
	}

	public RepService getRepService() {
		return repService;
	}

	public void setRepService(RepService repService) {
		this.repService = repService;
	}

	public Rep getRepPorNumeroSerie(Rep rep) throws ServiceException {
		if (rep != null) {
			return this.getRepService().buscarPorNumeroSerie(rep.getNumeroSerie());
		} else {
			throw new ServiceException(HttpStatus.NO_CONTENT, "Rep não cadastrado");
		}
	}

	public void receber(ComandoAbstract comandoAbstract, Rep rep) throws ServiceException {

		if (rep == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não autorizado");
		}
		// busca a referencia correta do rep
		rep = this.getRepService().buscarPorNumeroSerie(rep.getNumeroSerie());
		if (rep != null) {

			// configuracoes recebidas do Rep
			LOGGER.info("Receber e salvar dados");

		}

	}

	public RespostaSevidorDTO validarRespostaRep(RespostaRepDTO respostaRep, Rep repAutenticado) {
		RespostaSevidorDTO respostaSevidorDTO = new RespostaSevidorDTO();
		// se existe um NSU
		if (respostaRep.getNSU() != null && !respostaRep.getStatus().isEmpty()) {
			// busca o NSU
			Rep rep = this.getRepService().buscarPorNumeroSerie(repAutenticado.getNumeroSerie());
			if (rep != null) {
				// Teste basico, verifica se existe um status ok na resposta do
				// rep
				if (respostaRep.getStatus().stream().anyMatch(r -> r != CONSTANTES.STATUS_COM_REP.FALHA.ordinal())) {
					Tarefa tarefa = this.getTarefaRepository().findOne(respostaRep.getNSU());
					// se foi uma resposta de sucesso, excluir a Tarefa
					if (tarefa != null) {

						// Remove os vinculos
						tarefa = Tarefa.clear(tarefa);

						this.getTarefaRepository().save(tarefa);
						this.getTarefaRepository().delete(tarefa);
						respostaSevidorDTO.setHttpStatus(HttpStatus.OK);
						LOGGER.info("Tarefa NSU : " + tarefa.getNsu() + " removida");
					}
				}
			} else {
				respostaSevidorDTO.setHttpStatus(HttpStatus.NOT_MODIFIED);
			}
		}
		return respostaSevidorDTO;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public EmpregadoRespository getEmpregadoRespository() {
		return empregadoRespository;
	}

	public void setEmpregadoRespository(EmpregadoRespository empregadoRespository) {
		this.empregadoRespository = empregadoRespository;
	}

	public Rep getRep() {
		return rep;
	}

	public void setRep(Rep rep) {
		this.rep = rep;
	}

}
