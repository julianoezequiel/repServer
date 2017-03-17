package com.api.rep.service.rep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dao.ConfiguracoesRedeRepository;
import com.api.rep.dao.RepRepository;
import com.api.rep.dao.TarefaRepository;
import com.api.rep.dto.RepMonitor;
import com.api.rep.dto.comunicacao.RepDTO;
import com.api.rep.entity.ConfiguracoesRede;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ServiceException;
import com.api.rep.service.comandos.CmdHandler;

@Service
public class RepService {

	@Autowired
	private RepRepository repRepository;

	@Autowired
	private ConfiguracoesRedeRepository configuracoesRedeRepository;

	@Autowired
	private TarefaRepository tarefaRepository;

	// busca todos os rep existentes na base
	public Collection<RepDTO> buscarTodos() {
		Collection<Rep> reps = repRepository.findAll();
		Collection<RepDTO> listaDTO = new ArrayList<>();
		reps.stream().forEach(rep -> {
			listaDTO.add(new RepDTO(rep));
		});
		return listaDTO;
	}

	public Collection<Rep> buscarTodosRep() {
		return this.repRepository.findAll();
	}

	public Rep buscarPorNumeroSerie(String numSerie) {
		return this.repRepository.buscarPorNumeroSerie(numSerie);
	}

	// inclui um rep na base
	public RepDTO salvar(RepDTO repDTO) throws ServiceException {

		if (repDTO.getNumeroSerie() == null) {
			throw new ServiceException(HttpStatus.PRECONDITION_FAILED, "Número de série obrigatório");
		}
		if (this.buscarPorNumeroSerie(repDTO.getNumeroSerie()) != null) {
			throw new ServiceException(HttpStatus.PRECONDITION_FAILED, "Número de série já cadastrado");
		}
		
		Rep rep = repDTO.getRep();
		ConfiguracoesRede configuracoesRede = new ConfiguracoesRede();
		this.configuracoesRedeRepository.save(configuracoesRede);
		rep.setConfiguracoesRedeId(configuracoesRede);
		rep = this.repRepository.save(rep);
		//agenda o recebimento das configurações de Rede
		Tarefa tarefa = Tarefa.padraoTeste();
		tarefa.setRepId(rep);
		tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.CONFIG_REDE.ordinal());
		tarefa.setConfiguracoesRedeId(configuracoesRede);
		this.tarefaRepository.save(tarefa);

		return new RepDTO(rep);
	}

	public Rep salvar(Rep rep) {
		return this.repRepository.save(rep);
	}

	public String excluir(Integer id) throws ServiceException {
		Rep rep = this.repRepository.findOne(id);
		if (rep != null) {
			this.repRepository.delete(rep);
			return "Rep excluído com sucesso";
		} else {
			throw new ServiceException(HttpStatus.NO_CONTENT, "Rep não cadastrado");
		}
	}

	public Collection<RepMonitor> buscarRepMonitoramento() {
		List<RepMonitor> list = new ArrayList<>();
		this.repRepository.findAll().stream().forEach(r -> {
			list.add(r.toRepStatus());
		});
		return list;
	}

}
