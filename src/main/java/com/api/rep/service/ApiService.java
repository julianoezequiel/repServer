package com.api.rep.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.dao.EmpregadoRepository;
import com.api.rep.dao.TarefaRepository;
import com.api.rep.dto.comandos.ComandoAbstract;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.entity.Rep;
import com.api.rep.service.comandos.TratarResposta;
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
	private EmpregadoRepository empregadoRespository;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private TratarResposta tratarResposta;

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

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public EmpregadoRepository getEmpregadoRespository() {
		return empregadoRespository;
	}

	public void setEmpregadoRespository(EmpregadoRepository empregadoRespository) {
		this.empregadoRespository = empregadoRespository;
	}

	public Rep getRep() {
		return rep;
	}

	public void setRep(Rep rep) {
		this.rep = rep;
	}

	public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado) {
		// TODO Auto-generated method stub
		return null;
	}

}
