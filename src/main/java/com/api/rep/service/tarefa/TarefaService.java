package com.api.rep.service.tarefa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dao.TarefaRepository;
import com.api.rep.dto.comunicacao.RespostaRepDTO;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ApiService;

@Service
public class TarefaService extends ApiService {

	@Autowired
	private TarefaRepository tarefaRepository;

	public void excluir(Integer id) {
		this.getTarefaRepository().delete(id);
	}

	public void excluir(Tarefa tarefa) {
		this.getTarefaRepository().delete(tarefa);
	}

	public Tarefa getTarefa(Integer nsu) {
		List<Tarefa> list = this.tarefaRepository.buscarPorNsu(nsu);
		if (!list.isEmpty()) {
			return list.iterator().next();
		} else {
			return null;
		}
	}

	// @Override
	// public RespostaSevidorDTO validarRespostaRep(RespostaRepDTO respostaRep,
	// Rep repAutenticado) {
	// RespostaSevidorDTO respostaSevidorDTO = new RespostaSevidorDTO();
	// // se existe um NSU
	// if (respostaRep.getNSU() != null && !respostaRep.getStatus().isEmpty()) {
	// // busca o NSU
	// Rep rep =
	// this.getRepService().buscarPorNumeroSerie(repAutenticado.getNumeroSerie());
	// if (rep != null) {
	// // Teste basico, verifica se existe um status ok na resposta do
	// // rep
	// if (respostaRep.getStatus().stream()
	// .anyMatch(r -> r !=
	// CONSTANTES.STATUS_COM_REP.HTTPC_RESULT_FALHA.ordinal())) {
	// Tarefa tarefa = this.getTarefaRepository().findOne(respostaRep.getNSU());
	// respostaSevidorDTO =
	// TarefaHandler.TIPO_CMD.get(tarefa.getTipoTarefa()).tratarResposta(rep,
	// tarefa,
	// this);
	//
	/// * // se foi uma resposta de sucesso, excluir a Tarefa
	// if (tarefa != null) {
	//
	// // Remove os vinculos
	// tarefa = Tarefa.clear(tarefa);
	//
	// this.getTarefaRepository().save(tarefa);
	// this.getTarefaRepository().delete(tarefa);
	// respostaSevidorDTO.setHttpStatus(HttpStatus.OK);
	// LOGGER.info("Tarefa NSU : " + tarefa.getNsu() + " removida");
	// }*/
	// }
	// } else {
	// respostaSevidorDTO.setHttpStatus(HttpStatus.NOT_MODIFIED);
	// }
	// }
	// return respostaSevidorDTO;
	// }

}
