package com.api.rep.rest.comandos;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.dto.comunicacao.StatusColetaDTO;
import com.api.rep.entity.Nsr;
import com.api.rep.rest.ApiRestController;
import com.api.rep.service.ServiceException;
import com.api.rep.service.comandos.ColetaService;

@RestController
public class ColetaRestController extends ApiRestController {

	@Autowired
	private ColetaService coletaService;

	@RequestMapping(value = CONSTANTES.URL_COLETA, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<?> statusColeta(@RequestBody StatusColetaDTO statusColetaDTO) throws ServiceException {
		return new ResponseEntity<RespostaSevidorDTO>(
				this.coletaService.statusColeta(statusColetaDTO, this.getRepAutenticado()), HttpStatus.OK);
	}

	@RequestMapping(value = "coleta", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Collection<Nsr>> bucarPorRep() throws ServiceException {
		return new ResponseEntity<>(this.coletaService.buscarNsrPorRep(this.getRepAutenticado()), HttpStatus.OK);
	}

	@RequestMapping(value = "coleta/removertodos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> excluirTodos() throws ServiceException {
		HashMap<String, Long> total = new HashMap<>();
		total.put("Registro excluido", this.coletaService.excluirTodos(this.getRepAutenticado()));
		return new ResponseEntity<>(total, HttpStatus.OK);
	}

	@RequestMapping(value = "coleta/total/{numSerie}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> total(@PathVariable("numSerie") String numSerie) throws ServiceException {
		HashMap<String, Long> total = new HashMap<>();
		total.put("Total", this.coletaService.total(this.getRep(numSerie)));
		return new ResponseEntity<HashMap<String, Long>>(total, HttpStatus.OK);
	}

	/**
	 * Cancela um coleta em andamento
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "coleta/cancelar/{numSerie}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> cancelar(@PathVariable("numSerie") String numSerie) throws ServiceException {
		HashMap<String, Boolean> total = new HashMap<>();
		total.put("ColetaCmd cancelada", this.coletaService.cancelar(this.getRep(numSerie)));
		return new ResponseEntity<HashMap<String, Boolean>>(total, HttpStatus.OK);
	}

	/**
	 * Recebe do Rep os registro de NSR em formato texto
	 * 
	 * @param registros
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = CONSTANTES.URL_COLETA, consumes = { MediaType.APPLICATION_OCTET_STREAM_VALUE,
			"text/plain; charset=ISO-8859-1" }, method = RequestMethod.POST)
	public ResponseEntity<?> receber(@RequestBody String registros) throws ServiceException {
		this.coletaService.receber(registros, this.getRepAutenticado());
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}

	/**
	 * Recebe do Rep o dumping dos registros de NSR
	 * 
	 * @param nsu
	 * @param arquivoListaEmpregados
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = CONSTANTES.URL_COLETA_DUMPING
			+ "/{nsu}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receberDumping(@PathVariable("nsu") Integer nsu,
			@RequestParam("File") MultipartFile arquivoColeta) throws ServiceException {
		LOGGER.info("Recebendo dumping");
		this.coletaService.receberDumping(arquivoColeta, this.getRepAutenticado(), nsu);
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}

}
