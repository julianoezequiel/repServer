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
import com.api.rep.dao.ColetaRepository;
import com.api.rep.dao.ConfiguracoesSenhaRepository;
import com.api.rep.dao.EmpregadoRepository;
import com.api.rep.dao.EmpregadorRepository;
import com.api.rep.dao.InfoRepository;
import com.api.rep.dao.RepRepository;
import com.api.rep.dao.TarefaRepository;
import com.api.rep.dto.comandos.ColetaCmd;
import com.api.rep.dto.comandos.ConfiguracaoSenhaCmd;
import com.api.rep.dto.comandos.EmpregadoCmd;
import com.api.rep.dto.comandos.EmpregadorCmd;
import com.api.rep.entity.Coleta;
import com.api.rep.entity.ConfiguracoesSenha;
import com.api.rep.entity.Empregado;
import com.api.rep.entity.Empregador;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ServiceException;
import com.api.rep.service.tarefa.CmdHandler;

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
	private InfoRepository infoRepository;

	@RequestMapping(value = "coleta", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa coletaCmd(@RequestBody ColetaCmd coletaCmd) throws ServiceException {
		Tarefa tarefa = Tarefa.padraoTeste();

		Coleta coleta = coletaCmd.toColeta();
		coleta = this.coletaRepository.save(coleta);

		tarefa.setColetaId(coleta);
		tarefa.setRepId(this.repRepository.buscarPorNumeroSerie(this.getRepAutenticado().getNumeroSerie()));
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.COLETA.ordinal());

		return this.tarefaRepository.save(tarefa);
	}

	@RequestMapping(value = "empregado/{operacao}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa empregado(@RequestBody EmpregadoCmd empregadoCmd, @PathVariable Integer operacao)
			throws ServiceException {

		Rep rep = this.repRepository.buscarPorNumeroSerie(this.getRepAutenticado().getNumeroSerie());
		Tarefa tarefa = Tarefa.padraoTeste();

		Optional<Empregado> empregado = this.empregadoRepository.buscarPorPis(empregadoCmd.getfPis());
		if (empregado.isPresent()) {
			tarefa.setEmpregadoId(empregado.get());
		} else {
			Empregado empregado2 = empregadoCmd.toEmpregado();
			empregado2 = this.empregadoRepository.save(empregado2);
			tarefa.setEmpregadoId(empregado2);
		}

		tarefa.setRepId(rep);
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(operacao).ordinal());
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.EMPREGADO.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "empregador/{operacao}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa empregador(@RequestBody EmpregadorCmd empregadorCmd, @PathVariable Integer operacao)
			throws ServiceException {

		Rep rep = this.repRepository.buscarPorNumeroSerie(this.getRepAutenticado().getNumeroSerie());
		Tarefa tarefa = Tarefa.padraoTeste();

		Empregador empregador = empregadorCmd.toEmpregador();

		empregador.setId(rep.getEmpregadorId() != null ? rep.getEmpregadorId().getId() : null);

		empregador = this.empregadorRepository.save(empregador);
		rep.setEmpregadorId(empregador);
		this.repRepository.save(rep);
		tarefa.setEmpregadorId(empregador);
		tarefa.setRepId(rep);
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(operacao).ordinal());
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.EMPREGADOR.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "biometria/{operacao}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa biometria(@RequestBody EmpregadoCmd empregadoCmd, @PathVariable Integer operacao)
			throws ServiceException {

		Tarefa tarefa = Tarefa.padraoTeste();

		Empregado empregado = empregadoCmd.toEmpregado();
		empregado = this.empregadoRepository.findOne(Example.of(empregado));
		if (empregado != null) {
			tarefa.setEmpregadoId(empregado);
		}
		tarefa.setRepId(this.repRepository.buscarPorNumeroSerie(this.getRepAutenticado().getNumeroSerie()));
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(operacao).ordinal());
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.BIOMETRIA.ordinal());
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "listabiometria", method = RequestMethod.GET)
	public Tarefa listabiometria() throws ServiceException {
		Tarefa tarefa = new Tarefa();
		tarefa.setCpf(CONSTANTES.CPF_TESTE);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.LISTA_BIO.ordinal());
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		tarefa.setRepId(this.repRepository.buscarPorNumeroSerie(this.getRepAutenticado().getNumeroSerie()));
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "listaempregados", method = RequestMethod.GET)
	public Tarefa listaempreagados() throws ServiceException {
		Tarefa tarefa = Tarefa.padraoTeste();
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.LISTA_EMPREGADO.ordinal());
		tarefa.setRepId(this.repRepository.buscarPorNumeroSerie(this.getRepAutenticado().getNumeroSerie()));
		return this.tarefaRepository.save(tarefa);
	}

	@RequestMapping(value = "listaempregadosdumping/{operacao}", method = RequestMethod.POST)
	public Tarefa listaempreagadosdump(@PathVariable("operacao") Integer operacao) throws ServiceException {
		Tarefa tarefa = Tarefa.padraoTeste();
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.LISTA_EMPREGADO_DUMP.ordinal());
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(operacao).ordinal());
		tarefa.setRepId(this.repRepository.buscarPorNumeroSerie(this.getRepAutenticado().getNumeroSerie()));
		return this.tarefaRepository.save(tarefa);
	}

	@RequestMapping(value = "info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Tarefa info() throws ServiceException {

		Rep rep = this.repRepository.buscarPorNumeroSerie(this.getRepAutenticado().getNumeroSerie());
		Tarefa tarefa = Tarefa.padraoTeste();
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.INFO.ordinal());
		tarefa.setRepId(rep);
		return this.tarefaRepository.save(tarefa);

	}

	@RequestMapping(value = "configuracoes/senha/{operacao}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public Tarefa configSenha(@RequestBody ConfiguracaoSenhaCmd configuracaoSenhaCmd, @PathVariable Integer operacao)
			throws ServiceException {

		Tarefa tarefa = Tarefa.padraoTeste();

		List<ConfiguracoesSenha> configuracoesSenhaList = this.configuracoesSenhaRepository.findAll();
		ConfiguracoesSenha configuracoesSenha = configuracaoSenhaCmd.toConfigurcacoesSenha();
		if (!configuracoesSenhaList.isEmpty()) {
			configuracoesSenha.setId(configuracoesSenhaList.iterator().next().getId());
		}
		this.configuracoesSenhaRepository.save(configuracoesSenha);
		tarefa.setRepId(this.repRepository.buscarPorNumeroSerie(this.getRepAutenticado().getNumeroSerie()));
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(operacao).ordinal());
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_SENHA.ordinal());
		tarefa.setConfigurcacoesSenhaId(configuracoesSenha);
		return this.tarefaRepository.save(tarefa);

	}
}
