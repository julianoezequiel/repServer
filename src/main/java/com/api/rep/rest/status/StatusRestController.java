package com.api.rep.rest.status;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comandos.ComandoAbstract;
import com.api.rep.dto.comunicacao.TarefaDTO;
import com.api.rep.dto.comunicacao.RespostaRepDTO;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.dto.comunicacao.StatusDTO;
import com.api.rep.entity.Tarefa;
import com.api.rep.rest.ApiRestController;
import com.api.rep.service.ServiceException;
import com.api.rep.service.status.StatusService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = CONSTANTES.URL_STATUS)
public class StatusRestController extends ApiRestController {

	@Autowired
	private StatusService statusService;

	/**
	 * O Rep envia periodicamente o comando de status, atravez do método POST.
	 * Deve se retornar a última pendencia ou nada para O Rep. Caso seja
	 * retornada uma {@link TarefaDTO}, o Rep irá executar o
	 * {@link ComandoAbstract} informado na {@link TarefaDTO}.
	 * 
	 * 
	 * @param status
	 * @return {@link TarefaDTO}
	 * @throws ServiceException
	 * @throws JsonProcessingException
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<TarefaDTO> status(@RequestBody StatusDTO status)
			throws ServiceException, JsonProcessingException {
		ApiRestController.LOGGER.info("Status Recebido : " + this.getMapper().writeValueAsString(status));
		return new ResponseEntity<TarefaDTO>(this.statusService.validarStatus(status, this.getRepAutenticado()),
				HttpStatus.OK);
	}

	// Método somente para testes
	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Collection<TarefaDTO>> listar() throws ServiceException, JsonProcessingException {
		return new ResponseEntity<Collection<TarefaDTO>>(this.statusService.buscarTarefas(this.getRepAutenticado()),
				HttpStatus.OK);
	}

	// Método somente para testes
	@RequestMapping(value = "todas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Collection<Tarefa>> listarTodos() throws ServiceException {
		return new ResponseEntity<Collection<Tarefa>>(this.statusService.buscarTarefas(), HttpStatus.OK);
	}

	/**
	 * Retorno do status do comando e operacao realizada pelo Rep
	 * 
	 * @param empregador
	 * @return
	 * @throws ServiceException
	 * @throws JsonProcessingException
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<?> respostaRep(@RequestBody RespostaRepDTO respostaRep)
			throws ServiceException, JsonProcessingException {
		if (respostaRep != null && respostaRep.getStatus() != null) {
			LOGGER.info("Resposta Rep NSU : " + respostaRep.getNSU());
			respostaRep.getStatus().stream().forEach(r -> {
				LOGGER.info("Resposta Rep Status : " + CONSTANTES.STATUS_COM_REP.get(r));
			});
		}

		return new ResponseEntity<RespostaSevidorDTO>(
				this.statusService.validarRespostaRep(respostaRep, this.getRepAutenticado()), HttpStatus.OK);
	}
}
