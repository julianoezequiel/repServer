package com.api.rep.rest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dao.EmpregadoRespository;
import com.api.rep.dao.EmpregadorRepository;
import com.api.rep.dao.RepRepository;
import com.api.rep.dao.TarefaRepository;
import com.api.rep.dto.comunicacao.TarefaDTO;
import com.api.rep.entity.Coleta;
import com.api.rep.entity.Empregado;
import com.api.rep.entity.Empregador;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "agendarTarefa")
public class AgendarTarefa extends ApiRestController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private EmpregadoRespository empregadoRespository;

	@Autowired
	private EmpregadorRepository empregadorRepository;

	@Autowired
	private RepRepository repRepository;

	@RequestMapping(value = "{tipo}/{operacao}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> status(@PathVariable Integer tipo, @PathVariable Integer operacao)
			throws ServiceException, JsonProcessingException {
		ApiRestController.LOGGER.info("Solicitando comando : " + CONSTANTES.TIPO_CMD.get(tipo));

		Tarefa tarefa;
		Rep rep = this.getRepAutenticado();
		if (rep != null && rep.getNumeroSerie() != null) {
			rep = this.repRepository.buscarPorNumeroSerie(rep.getNumeroSerie());

			switch (CONSTANTES.TIPO_CMD.get(tipo)) {
			case BIOMETRIA:
				tarefa = new Tarefa();
				tarefa.setCpf(CONSTANTES.CPF_TESTE);
				tarefa.setTipoTarefa(CONSTANTES.TIPO_CMD.BIOMETRIA.ordinal());
				
				tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(operacao).ordinal());
				
				tarefa.setRepId(rep);

				Optional<Empregado> empregadoOpt = this.empregadoRespository.buscarPorPis("010892044889");
				if (empregadoOpt.isPresent()) {
					tarefa.setEmpregadoId(empregadoOpt.get());
					this.tarefaRepository.save(tarefa);
				}
				break;

			case COLETA:

				tarefa = Tarefa.padraoTeste();
				tarefa.setColetaId(new Coleta(1, 1000));

				this.tarefaRepository.save(tarefa);

				break;

			case CONFIG:

				break;

			case EMPREGADO:

				List<Empregado> empregados = this.empregadoRespository.findAll();
				if (!empregados.isEmpty()) {
					tarefa = Tarefa.padraoTeste();
					if (!empregados.isEmpty()) {
						Empregado e = empregados.get(new Random().nextInt(empregados.size()));
						tarefa.setEmpregadoId(e);
						LOGGER.info("Agendar solicitação empregado PIS :" + e.getEmpregadoPis());
						tarefa.setRepId(rep);
						tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(operacao).ordinal());
						tarefa.setTipoTarefa(CONSTANTES.TIPO_CMD.EMPREGADO.ordinal());
						this.tarefaRepository.save(tarefa);
					}
				}
				break;

			case EMPREGADOR:

				Empregador empregador = rep.getEmpregadorId();

				if (operacao == 2) {
					empregador.setEmpregadorCei(ceiRandom());
				}
				if (empregador != null) {
					tarefa = Tarefa.padraoTeste();
					tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(operacao).ordinal());
					tarefa.setTipoTarefa(CONSTANTES.TIPO_CMD.EMPREGADOR.ordinal());
					tarefa.setEmpregadorId(empregador);
					tarefa.setRepId(rep);
					this.tarefaRepository.save(tarefa);
				}
				break;

			case INFO:

				break;

			case LISTA_BIOMETRIA:

				tarefa = new Tarefa();
				tarefa.setCpf(CONSTANTES.CPF_TESTE);
				tarefa.setTipoTarefa(CONSTANTES.TIPO_CMD.LISTA_BIOMETRIA.ordinal());
				tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(operacao).ordinal());
				tarefa.setRepId(rep);
				this.tarefaRepository.save(tarefa);

				break;

			case RELOGIO:

				break;

			case CMD_NENHUM:
			default:
				break;
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			throw new ServiceException(HttpStatus.UNAUTHORIZED);
		}
	}

	private String ceiRandom() {
		Integer i = (int) (Math.random() * (999999999 - 1)) + 999999999;
		return i.toString();
	}

}
