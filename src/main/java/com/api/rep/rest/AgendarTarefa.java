package com.api.rep.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dao.AjusteBioRepository;
import com.api.rep.dao.ColetaDumpingRepository;
import com.api.rep.dao.ColetaRepository;
import com.api.rep.dao.ConfiguracoesCartoesRepository;
import com.api.rep.dao.ConfiguracoesRedeRepository;
import com.api.rep.dao.ConfiguracoesSenhaRepository;
import com.api.rep.dao.ConfiguracoesWebServerRepository;
import com.api.rep.dao.EmpregadoRepository;
import com.api.rep.dao.EmpregadorRepository;
import com.api.rep.dao.HorarioVeraoRepository;
import com.api.rep.dao.RelogioRepository;
import com.api.rep.dao.RepRepository;
import com.api.rep.dao.TarefaRepository;
import com.api.rep.dto.comandos.AjustesBioCmd;
import com.api.rep.dto.comandos.ColetaCmd;
import com.api.rep.dto.comandos.ColetaDumpingCmd;
import com.api.rep.dto.comandos.ConfiguracaoSenhaCmd;
import com.api.rep.dto.comandos.ConfiguracoesCartoesCmd;
import com.api.rep.dto.comandos.ConfiguracoesRedeCmd;
import com.api.rep.dto.comandos.ConfiguracacoesWebServerCmd;
import com.api.rep.dto.comandos.EmpregadoCmd;
import com.api.rep.dto.comandos.EmpregadorCmd;
import com.api.rep.dto.comandos.HorarioVeraoCmd;
import com.api.rep.dto.comandos.RelogioCmd;
import com.api.rep.entity.AjustesBio;
import com.api.rep.entity.Coleta;
import com.api.rep.entity.ColetaDumping;
import com.api.rep.entity.ConfiguracoesCartoes;
import com.api.rep.entity.ConfiguracoesRede;
import com.api.rep.entity.ConfiguracoesSenha;
import com.api.rep.entity.ConfigurcacoesWebServer;
import com.api.rep.entity.Empregado;
import com.api.rep.entity.Empregador;
import com.api.rep.entity.HorarioVerao;
import com.api.rep.entity.Relogio;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ServiceException;
import com.api.rep.service.comandos.CmdHandler;

@RestController
@RequestMapping(value = "agendarTarefa")
public class AgendarTarefa extends ApiRestController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private EmpregadoRepository empregadoRepository;

	@Autowired
	private EmpregadorRepository empregadorRepository;

	@Autowired
	private ColetaRepository coletaRepository;

	@Autowired
	private RepRepository repRepository;

	@Autowired
	private ConfiguracoesSenhaRepository configuracoesSenhaRepository;

	@Autowired
	private RelogioRepository relogioRepository;

	@Autowired
	private HorarioVeraoRepository horarioVeraoRepository;

	@Autowired
	private ConfiguracoesCartoesRepository configuracoesCartoesRepository;

	@Autowired
	private ConfiguracoesRedeRepository configuracoesRedeRepository;

	@Autowired
	private AjusteBioRepository ajusteBioRepository;

	@Autowired
	private ConfiguracoesWebServerRepository configuracoesWebServerRepository;

	@Autowired
	private ColetaDumpingRepository coletaDumpingRepository;

	@RequestMapping(value = "coleta/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa coletaCmd(@PathVariable("numSerie") String numSerie, @RequestBody ColetaCmd coletaCmd)
			throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.RECEBER.name(), rep);

		Coleta coleta = coletaCmd.toColeta();
		coleta = this.coletaRepository.save(coleta);

		tarefa.setColetaId(coleta);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.COLETA.ordinal());

		return this.tarefaRepository.save(tarefa);
	}

	@RequestMapping(value = "coletadump/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa coletaCmd(@PathVariable("numSerie") String numSerie, @RequestBody ColetaDumpingCmd coletaDumpingCmd)
			throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.RECEBER.name(), rep);

		ColetaDumping coletaDumping = coletaDumpingCmd.toColetaDumping();

		coletaDumping = this.coletaDumpingRepository.save(coletaDumping);

		tarefa.setColetaDumpingId(coletaDumping);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.COLETA_DUMPING.ordinal());

		return this.tarefaRepository.save(tarefa);
	}

	@RequestMapping(value = "empregado/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa empregado(@PathVariable("numSerie") String numSerie, @RequestBody EmpregadoCmd empregadoCmd,
			@PathVariable String operacao) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);

		Optional<Empregado> empregado = this.empregadoRepository.buscarPorPis(empregadoCmd.getfPis(), rep);
		Empregado empregado2 = empregadoCmd.toEmpregado();
		if (empregado.isPresent()) {
			empregado2.setId(empregado.get().getId());
		}
		empregado2 = this.empregadoRepository.save(empregado2);
		tarefa.setEmpregadoId(empregado2);

		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.EMPREGADO.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "empregador/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa empregador(@PathVariable("numSerie") String numSerie, @RequestBody EmpregadorCmd empregadorCmd,
			@PathVariable String operacao) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);
		Empregador empregador = empregadorCmd.toEmpregador();
		empregador.setId(rep.getEmpregadorId() != null ? rep.getEmpregadorId().getId() : null);
		empregador = this.empregadorRepository.save(empregador);

		rep.setEmpregadorId(empregador);
		this.repRepository.save(rep);

		tarefa.setEmpregadorId(empregador);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.EMPREGADOR.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "biometria/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa biometria(@PathVariable("numSerie") String numSerie, @RequestBody EmpregadoCmd empregadoCmd,
			@PathVariable String operacao) throws ServiceException {

		Rep rep = this.getRep(numSerie);

		Tarefa tarefa = tarefaTeste(operacao, rep);

		Empregado empregado = empregadoCmd.toEmpregado();
		empregado = this.empregadoRepository.findOne(Example.of(empregado));
		if (empregado != null) {
			tarefa.setEmpregadoId(empregado);
		}
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.BIOMETRIA.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "listabiometria/{numSerie}", method = RequestMethod.GET)
	public Tarefa listabiometria(@PathVariable("numSerie") String numSerie) throws ServiceException {
		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.RECEBER.name(), rep);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.LISTA_BIO.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "listaempregados/{numSerie}", method = RequestMethod.GET)
	public Tarefa listaempregados(@PathVariable("numSerie") String numSerie) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.RECEBER.name(), rep);

		long exluidos = this.empregadoRepository.removeByrepId(rep);
		LOGGER.info("Lista de empregados excluida da base , total de excluidos " + exluidos);

		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.LISTA_EMPREGADO.ordinal());
		return this.tarefaRepository.save(tarefa);
	}

	@RequestMapping(value = "listaempregadosdump/{operacao}/{numSerie}", method = RequestMethod.POST)
	public Tarefa listaempreagadosdump(@PathVariable("numSerie") String numSerie,
			@PathVariable("operacao") String operacao) throws ServiceException {
		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.LISTA_EMPREGADO_DUMP.ordinal());
		return this.tarefaRepository.save(tarefa);
	}

	@RequestMapping(value = "info/{numSerie}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Tarefa info(@PathVariable("numSerie") String numSerie) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.ENVIAR.name(), rep);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.INFO.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "atualizarfirmware/{numSerie}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Tarefa atualizarFw(@PathVariable("numSerie") String numSerie) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.ENVIAR.name(), rep);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.ATUALIZACAO_FW.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "atualizarfirmwaretodos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public void atualizarFwTodos() throws ServiceException {

		List<Rep> repList = this.repRepository.findAll();
		repList.stream().forEach(r -> {
			Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.ENVIAR.name(), r);
			tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.ATUALIZACAO_FW.ordinal());
			tarefa.setRepId(r);
			this.tarefaRepository.save(tarefa);
		});

	}

	@RequestMapping(value = "atualizarpaginas/{numSerie}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Tarefa atualizarPaginas(@PathVariable("numSerie") String numSerie) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.ENVIAR.name(), rep);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.ATUALIZACAO_PAGINAS.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "atualizarpaginastodos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public void atualizarPaginasTodos() throws ServiceException {
		List<Rep> repList = this.repRepository.findAll();
		repList.stream().forEach(r -> {
			Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.ENVIAR.name(), r);
			tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.ATUALIZACAO_PAGINAS.ordinal());
			this.tarefaRepository.save(tarefa);
		});
	}

	@RequestMapping(value = "identificadores/{numSerie}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Tarefa identificadores(@PathVariable("numSerie") String numSerie) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(CONSTANTES.TIPO_OPERACAO.RECEBER.name(), rep);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.IDENTFICACAO.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "configuracoes/senha/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa configSenha(@PathVariable("numSerie") String numSerie,
			@RequestBody ConfiguracaoSenhaCmd configuracaoSenhaCmd, @PathVariable String operacao)
			throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);

		ConfiguracoesSenha configuracoesSenha = configuracaoSenhaCmd.toConfigurcacoesSenha();
		configuracoesSenha
				.setId(rep.getConfigurcacoesSenhaId() != null ? rep.getConfigurcacoesSenhaId().getId() : null);
		this.configuracoesSenhaRepository.save(configuracoesSenha);

		rep.setConfigurcacoesSenhaId(configuracoesSenha);
		rep = this.repRepository.save(rep);

		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_SENHA.ordinal());
		tarefa.setConfigurcacoesSenhaId(configuracoesSenha);
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "relogio/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa relogio(@PathVariable("numSerie") String numSerie, @RequestBody RelogioCmd relogioCmd,
			@PathVariable String operacao) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);

		Relogio relogio = relogioCmd.toRelogio();
		relogio.setId(rep.getRelogioId() != null ? rep.getRelogioId().getId() : null);
		this.relogioRepository.save(relogio);

		rep.setRelogioId(relogio);
		rep = this.repRepository.save(rep);

		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_RELOGIO.ordinal());
		tarefa.setRelogioId(relogio);

		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "horarioverao/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa horarioverao(@PathVariable("numSerie") String numSerie, @RequestBody HorarioVeraoCmd horarioVeraoCmd,
			@PathVariable String operacao) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);

		HorarioVerao horarioVerao = horarioVeraoCmd.toHorarioVerao();
		horarioVerao.setId(rep.getHorarioVeraoId() != null ? rep.getHorarioVeraoId().getId() : null);
		this.horarioVeraoRepository.save(horarioVerao);

		rep.setHorarioVeraoId(horarioVerao);
		rep = this.repRepository.save(rep);

		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_HORARIO_VERAO.ordinal());
		tarefa.setHorarioVeraoId(horarioVerao);
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "cartoes/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa cartoes(@PathVariable("numSerie") String numSerie,
			@RequestBody ConfiguracoesCartoesCmd configuracoesCartoesCmd, @PathVariable String operacao)
			throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);

		ConfiguracoesCartoes configuracoesCartoes = configuracoesCartoesCmd.toConfiguracoesCartoes();
		configuracoesCartoes
				.setId(rep.getConfiguracoesCartoesId() != null ? rep.getConfiguracoesCartoesId().getId() : null);

		this.configuracoesCartoesRepository.save(configuracoesCartoes);

		rep.setConfiguracoesCartoesId(configuracoesCartoes);
		rep = this.repRepository.save(rep);

		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_CARTOES.ordinal());
		tarefa.setConfiguracoesCartoesId(configuracoesCartoes);
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "rede/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa rede(@PathVariable("numSerie") String numSerie,
			@RequestBody ConfiguracoesRedeCmd configuracoesRedeCmd, @PathVariable String operacao)
			throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);

		ConfiguracoesRede configuracoesRede = configuracoesRedeCmd.toConfiguracoesRede();
		configuracoesRede.setId(rep.getConfiguracoesRedeId() != null ? rep.getConfiguracoesRedeId().getId() : null);

		this.configuracoesRedeRepository.save(configuracoesRede);

		rep.setConfiguracoesRedeId(configuracoesRede);
		rep = this.repRepository.save(rep);

		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_REDE.ordinal());
		tarefa.setConfiguracoesRedeId(configuracoesRede);
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "ajustebio/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa ajusteBio(@PathVariable("numSerie") String numSerie, @RequestBody AjustesBioCmd ajustesBioCmd,
			@PathVariable String operacao) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);

		AjustesBio ajustesBio = ajustesBioCmd.toAjustesBio();
		ajustesBio.setId(rep.getAjustesBioId() != null ? rep.getAjustesBioId().getId() : null);

		this.ajusteBioRepository.save(ajustesBio);

		rep.setAjustesBioId(ajustesBio);
		rep = this.repRepository.save(rep);

		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_BIO.ordinal());
		tarefa.setAjustesBioId(ajustesBio);
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "verificacao/{numSerie}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Tarefa verificacao(@PathVariable("numSerie") String numSerie) throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste("enviar", rep);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.VERIFICA_LISTA_BIO.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "configwebserve/{operacao}/{numSerie}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa configWebServer(@PathVariable("numSerie") String numSerie,
			@RequestBody ConfiguracacoesWebServerCmd configuracacoesWebServerCmd, @PathVariable String operacao)
			throws ServiceException {

		Rep rep = this.getRep(numSerie);
		Tarefa tarefa = tarefaTeste(operacao, rep);

		ConfigurcacoesWebServer configurcacoesWebServer = configuracacoesWebServerCmd.toConfigurcacoesWebServer();
		configurcacoesWebServer
				.setId(rep.getConfigurcacoesWebServerId() != null ? rep.getConfigurcacoesWebServerId().getId() : null);

		this.configuracoesWebServerRepository.save(configurcacoesWebServer);

		rep.setConfigurcacoesWebServerId(configurcacoesWebServer);
		rep = this.repRepository.save(rep);

		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_WEB_SERVER.ordinal());
		tarefa.setConfigurcacoesWebServerId(configurcacoesWebServer);
		return this.tarefaRepository.save(tarefa);

	}

	public Tarefa tarefaTeste(String operacao, Rep rep) {

		Tarefa tarefa = new Tarefa();
		tarefa.setCpf(CONSTANTES.CPF_TESTE);
		tarefa.setTipoOperacao(getOperacao(operacao));
		tarefa.setRepId(rep);

		return tarefa;
	}

	public int getOperacao(String s) {
		if (s.equals("receber")) {
			return CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal();
		} else if (s.equalsIgnoreCase("enviar")) {
			return CONSTANTES.TIPO_OPERACAO.ENVIAR.ordinal();
		} else if (s.equalsIgnoreCase("excluir")) {
			return CONSTANTES.TIPO_OPERACAO.EXCLUIR.ordinal();
		} else {
			return CONSTANTES.TIPO_OPERACAO.NENHUMA.ordinal();
		}
	}

}
