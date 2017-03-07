package com.api.rep.service.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.contantes.CONSTANTES.TIPO_CMD;
import com.api.rep.contantes.CONSTANTES.TIPO_OPERACAO;
import com.api.rep.contantes.DEF;
import com.api.rep.dao.ColetaRepository;
import com.api.rep.dao.ConfiguracaoRepository;
import com.api.rep.dao.NsrRepository;
import com.api.rep.dto.comunicacao.StatusDTO;
import com.api.rep.dto.comunicacao.TarefaDTO;
import com.api.rep.entity.Coleta;
import com.api.rep.entity.Configuracao;
import com.api.rep.entity.Nsr;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ApiService;
import com.api.rep.service.ComandosTeste;
import com.api.rep.service.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class StatusService extends ApiService {

	@Autowired
	private NsrRepository nsrRepository;

	@Autowired
	private ColetaRepository coletaRepository;

	@Autowired
	private ConfiguracaoRepository configuracaoRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(StatusService.class.getName());

	/**
	 * @param status
	 * @param rep
	 * @return
	 * @throws ServiceException
	 * @throws JsonProcessingException
	 */
	public TarefaDTO validarStatus(StatusDTO status, Rep rep) throws ServiceException, JsonProcessingException {

		rep = validarDadosEntrada(status, rep);

		// utilizar para teste, retornar um comando fixo da classe
		// "ComandosTeste"
		if (DEF.TAREFA_CMD_FIXO == Boolean.TRUE) {
			// para o envio manual de uma pendencia do tipo Empregado
			return ComandosTeste.enviarEmpregado();

			// para o envio manual de uma pendencia do tipo Empregador
			// return ComandosTeste.enviarEmpregador();

		}

		// se o agendamento auto das configurcaoes estiver habilitada
		if (DEF.TAREFA_CONFIG_AUTO) {
			agendarReceberConfiguracao(rep, status);
		}
		// se o agendamento auto da coleta estiver habilitada
		if (DEF.TAREFA_COLETA_AUTO) {
			agendarReceberColeta(rep, status);
		}

		// retorna a ultima Tarefa para o rep
		return proximaTarefa(rep);

	}

	public Collection<TarefaDTO> buscarTarefas(Rep rep) {

		if (rep != null && rep.getNumeroSerie() != null) {
			rep = this.getRepService().buscarPorNumeroSerie(rep.getNumeroSerie());
		}

		Collection<TarefaDTO> tarefasList = new ArrayList<>();

		if (rep != null && !rep.getTarefaCollection().isEmpty()) {
			rep.getTarefaCollection().stream().forEach(r -> {
				tarefasList.add(r.toTarefaDTO());
			});

			return tarefasList;

		} else {
			return null;
		}
	}

	public Collection<Tarefa> buscarTarefas() {
		return this.getTarefaRepository().findAll();
	}

	// busca a ultima Tarefa para Rep executar
	public TarefaDTO proximaTarefa(Rep rep) throws ServiceException {

		rep = this.getRepService().buscarPorNumeroSerie(rep.getNumeroSerie());

		TarefaDTO dto = rep.getTarefaCollection().iterator().hasNext()
				? rep.getTarefaCollection().iterator().next().toTarefaDTO() : null;

		if (dto != null) {
			LOGGER.info("Tarefa NSU : {} - Tipo Tarefa : {} - Operação : {}",
					new Object[] { dto.getNsu(), CONSTANTES.TIPO_CMD.get(dto.getTipoComando()),
							CONSTANTES.TIPO_OPERACAO.get(dto.getTipoOperacao()) });
			return dto;
		} else {
			dto = new TarefaDTO();
			dto.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.NENHUMA.ordinal());
			LOGGER.info("Sem pendencia");
			return dto;
		}
	}

	private Rep validarDadosEntrada(StatusDTO status, Rep rep) throws ServiceException {
		if (status == null || status.getUltimoNsr() == null || status.getUltimoNsr() == null) {
			throw new ServiceException(HttpStatus.PARTIAL_CONTENT, "Status inválido");
		}

		if (rep == null) {
			throw new ServiceException(HttpStatus.EXPECTATION_FAILED, "Erro ao realizar a operação");
		}

		// busca na base a referencia do rep
		rep = this.getRepService().buscarPorNumeroSerie(rep.getNumeroSerie());
		if (rep == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não cadastrado");
		}

		return rep;

	}

	private void agendarReceberConfiguracao(Rep rep, StatusDTO status) {

		if (status.getConfig() == true) {

			List<Tarefa> tarefaList = this.getTarefaRepository().buscarPorRep(rep);

			Configuracao configuracao = new Configuracao();
			Tarefa tarefa = tarefaList.stream()
					.filter(p -> p.getNsu() != null && (p.getTipoTarefa().equals(TIPO_CMD.CONFIG.ordinal())
							&& p.getTipoOperacao().equals(TIPO_OPERACAO.RECEBER.ordinal())))
					.findFirst().orElse(new Tarefa());

			// se ja existe uma Tarefa do tipo Configuracao, não agenda
			// //
			// outra solicitação
			if (tarefa.getColetaId() == null) {
				tarefa.setTipoOperacao(TIPO_OPERACAO.RECEBER.ordinal());
				tarefa.setTipoTarefa(TIPO_CMD.CONFIG.ordinal());
				tarefa.setConfiguracaoId(this.configuracaoRepository.save(configuracao));
				tarefa.setRepId(rep);
				this.getTarefaRepository().save(tarefa);
			}
		}
	}

	private void agendarReceberColeta(Rep rep, StatusDTO status) {

		// ultimo Nsr
		Nsr ultimoNsr = this.nsrRepository.findLast(); //

		// se a lista esta vazia adiciona ou o ultimo nsr da base < que o
		// ultimo nsr do rep? a lista de Tarefa
		Integer inicio = 1295259 - 100;
		if (ultimoNsr == null) {
			ultimoNsr = new Nsr();
			ultimoNsr.setNumeroNsr(1);
			ultimoNsr.setNumeroNsr(ultimoNsr.getNumeroNsr() < inicio ? inicio : ultimoNsr.getNumeroNsr());
		} else {
			ultimoNsr.setNumeroNsr(ultimoNsr.getNumeroNsr() < inicio ? inicio : ultimoNsr.getNumeroNsr());
		}

		if (ultimoNsr.getNumeroNsr() < status.getUltimoNsr()) {

			List<Tarefa> tarefaList = this.getTarefaRepository().buscarPorRep(rep);

			Tarefa tarefa = tarefaList.stream()
					.filter(p -> p.getNsu() != null && (p.getTipoTarefa().equals(TIPO_CMD.COLETA.ordinal())
							&& p.getTipoOperacao().equals(TIPO_OPERACAO.RECEBER.ordinal())))
					.findFirst().orElse(new Tarefa());
			// se ja existe uma Tarefa do tipo coleta, atualiza o range
			// de coleta
			if (tarefa.getColetaId() != null) {
				tarefa.getColetaId().setColetaNsrInicio(ultimoNsr.getNumeroNsr());
				tarefa.getColetaId().setColetaNsrFim(status.getUltimoNsr());
			} else {
				tarefa.setRepId(rep);
				tarefa.setColetaId(
						this.coletaRepository.save(new Coleta(ultimoNsr.getNumeroNsr(), status.getUltimoNsr())));
				tarefa.setTipoTarefa(TIPO_CMD.COLETA.ordinal());
				tarefa.setTipoOperacao(TIPO_OPERACAO.RECEBER.ordinal());
			}

			this.getTarefaRepository().save(tarefa);
		}
	}

}
