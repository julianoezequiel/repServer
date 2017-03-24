package com.api.rep.service.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.contantes.CONSTANTES.TIPO_OPERACAO;
import com.api.rep.dao.ColetaRepository;
import com.api.rep.dao.ConfiguracoesRedeRepository;
import com.api.rep.dao.NsrRepository;
import com.api.rep.dto.comunicacao.ComandoDeEnvio;
import com.api.rep.dto.comunicacao.RespostaRepDTO;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.dto.comunicacao.StatusDTO;
import com.api.rep.entity.Coleta;
import com.api.rep.entity.ConfiguracoesRede;
import com.api.rep.entity.Nsr;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;
import com.api.rep.service.comandos.CmdHandler;
import com.api.rep.service.comandos.ColetaService;
import com.api.rep.service.rep.RepService;
import com.api.rep.service.tarefa.TarefaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class StatusService extends ApiService {

	private final static Logger LOGGER = LoggerFactory.getLogger(StatusService.class.getName());

	@Autowired
	private NsrRepository nsrRepository;

	@Autowired
	private ColetaRepository coletaRepository;

	@Autowired
	private ConfiguracoesRedeRepository configuracoesRedeRepository;

	@Autowired
	private RepService repService;

	@Autowired
	private TarefaService tarefaService;

	@Autowired
	private ColetaService coletaService;

	@Value("${nsr.inicial}")
	Integer inicio;

	@Value("${coleta.auto}")
	Boolean coletaAuto;

	@Value("${coleta.config}")
	Boolean coletaConfig;

	/**
	 * @param status
	 * @param rep
	 * @return
	 * @throws ServiceException
	 * @throws JsonProcessingException
	 */
	public ComandoDeEnvio validarStatus(HttpServletRequest request, StatusDTO status, Rep rep) throws ServiceException {

		rep = validarDadosEntrada(status, rep);

		atualizarStatus(rep, request);

		// agendamento auto das configuracoes estiver habilitada
		if (coletaConfig) {
			agendarReceberConfiguracao(rep, status);
		}
		// agendamento auto da coleta estiver habilitada
		if (coletaAuto) {
			agendarReceberColeta(rep, status);
		}

		// retorna a ultima Tarefa para o rep
		return proximaTarefa(rep);

	}

	public Collection<ComandoDeEnvio> buscarTarefas(Rep rep) {

		if (rep != null && rep.getNumeroSerie() != null) {
			rep = this.getRepService().buscarPorNumeroSerie(rep.getNumeroSerie());
		}

		Collection<ComandoDeEnvio> tarefasList = new ArrayList<>();

		if (rep != null && !rep.getTarefaCollection().isEmpty()) {
			rep.getTarefaCollection().stream().forEach(r -> {
				tarefasList.add(r.toComandoDeEnvio());
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
	public ComandoDeEnvio proximaTarefa(Rep rep) throws ServiceException {

		rep = this.getRepService().buscarPorNumeroSerie(rep.getNumeroSerie());

		ComandoDeEnvio dto = rep.getTarefaCollection().iterator().hasNext()
				? rep.getTarefaCollection().iterator().next().toComandoDeEnvio() : null;

		if (dto != null) {
			LOGGER.info("Tarefa NSU : {} - Tipo Tarefa : {} - Operação : {}", new Object[] { dto.getNsu(),
					CmdHandler.TIPO_CMD.get(dto.gettCmd()), CONSTANTES.TIPO_OPERACAO.get(dto.gettOp()) });
			LOGGER.info("------------------------");
			return dto;
		} else {
			dto = new ComandoDeEnvio();
			dto.settOp(CONSTANTES.TIPO_OPERACAO.NENHUMA.ordinal());
			LOGGER.info("Sem pendencia");
			LOGGER.info("------------------------");
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
		rep = this.getRepPorNumeroSerie(rep);

		return rep;

	}

	private void agendarReceberConfiguracao(Rep rep, StatusDTO status) {
		// TODO : colocar o tipo especifico de configurações
		if (status.getConfig() == true) {

			List<Tarefa> tarefaList = this.getTarefaRepository().buscarPorRep(rep);

			ConfiguracoesRede configuracoesRede = new ConfiguracoesRede();
			Tarefa tarefa = tarefaList.stream().filter(
					p -> p.getNsu() != null && (p.getTipoTarefa().equals(CmdHandler.TIPO_CMD.CONFIG_SENHA.ordinal())
							&& p.getTipoOperacao().equals(TIPO_OPERACAO.RECEBER.ordinal())))
					.findFirst().orElse(new Tarefa());

			// se ja existe uma Tarefa do tipo ConfiguracoesRede, não agenda
			// //
			// outra solicitação
			if (tarefa.getColetaId() == null) {
				tarefa.setTipoOperacao(TIPO_OPERACAO.RECEBER.ordinal());
				tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_SENHA.ordinal());
				tarefa.setConfiguracoesRedeId(this.configuracoesRedeRepository.save(configuracoesRede));
				tarefa.setRepId(rep);
				this.getTarefaRepository().save(tarefa);
			}
		}
	}

	private void agendarReceberColeta(Rep rep, StatusDTO status) {

		// ultimo Nsr
		Nsr ultimoNsrColetado = this.nsrRepository.buscarUltimoNsr(rep.getId()); //

		if (ultimoNsrColetado == null) {
			ultimoNsrColetado = new Nsr();
			ultimoNsrColetado.setNumeroNsr(status.getUltimoNsr() > 500 ? status.getUltimoNsr() - 500 : 0);
		} else if (ultimoNsrColetado.getNumeroNsr() + 500 < status.getUltimoNsr()) {
			ultimoNsrColetado.setNumeroNsr(status.getUltimoNsr() - 500);
		}

		if (ultimoNsrColetado.getNumeroNsr() < status.getUltimoNsr()) {

			List<Tarefa> tarefaList = this.getTarefaRepository().buscarPorRep(rep);

			Tarefa tarefa = tarefaList.stream()
					.filter(p -> p.getNsu() != null && (p.getTipoTarefa().equals(CmdHandler.TIPO_CMD.COLETA.ordinal())
							&& p.getTipoOperacao().equals(TIPO_OPERACAO.RECEBER.ordinal())))
					.findFirst().orElse(new Tarefa());
			// se ja existe uma Tarefa do tipo coleta, atualiza o range
			// de coleta
			if (tarefa.getColetaId() != null) {
				tarefa.getColetaId().setColetaNsrInicio(ultimoNsrColetado.getNumeroNsr());
				tarefa.getColetaId().setColetaNsrFim(status.getUltimoNsr());
			} else {
				tarefa.setRepId(rep);
				tarefa.setColetaId(this.coletaRepository
						.save(new Coleta(ultimoNsrColetado.getNumeroNsr(), status.getUltimoNsr())));
				tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.COLETA.ordinal());
				tarefa.setTipoOperacao(TIPO_OPERACAO.RECEBER.ordinal());
			}

			this.getTarefaRepository().save(tarefa);
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
				Tarefa tarefa = this.getTarefaRepository().findOne(respostaRep.getNSU());

				if (respostaRep.getStatus().stream()
						.anyMatch(r -> r != CONSTANTES.STATUS_COM_REP.HTTPC_RESULT_FALHA.ordinal())
						|| tarefa.getTipoTarefa().equals(CmdHandler.TIPO_CMD.ATUALIZACAO_FW.ordinal())
						|| tarefa.getTipoTarefa().equals(CmdHandler.TIPO_CMD.ATUALIZACAO_PAGINAS.ordinal())
						|| tarefa.getTipoTarefa().equals(CmdHandler.TIPO_CMD.COLETA.ordinal())
						|| (tarefa.getTentativas() != null && tarefa.getTentativas() > 3)) {

					// se foi uma resposta de sucesso, excluir a Tarefa
					if (tarefa != null) {
						// salva o arquivo do dump da coleta
						if (tarefa.getTipoTarefa().equals(CmdHandler.TIPO_CMD.COLETA_DUMPING.ordinal())) {
							coletaService.salvarArquivoDumpEmDisco(tarefa.getNsu());
						}

						// Remove os vínculos
						tarefa = Tarefa.clear(tarefa);
						this.getTarefaRepository().save(tarefa);
						this.getTarefaRepository().delete(tarefa);
						respostaSevidorDTO.setHttpStatus(HttpStatus.OK);
						LOGGER.info("Tarefa NSU : " + tarefa.getNsu() + " removida");

					}
				} else {
					// Integer[] s = (Integer[])
					// respostaRep.getStatus().toArray();
					// tarefa.setStatus(s);
					tarefa.setTentativas(tarefa.getTentativas() == null ? 0 : tarefa.getTentativas() + 1);
					this.getTarefaRepository().save(tarefa);
				}

			} else {
				respostaSevidorDTO.setHttpStatus(HttpStatus.UNAUTHORIZED);
			}
		}
		return respostaSevidorDTO;
	}

	private void atualizarStatus(Rep rep, HttpServletRequest request) {
		rep.setUltimoIp(request.getRemoteAddr());
		rep.setUltimaConexao(new Date());
		this.repService.salvar(rep);
	}

}
