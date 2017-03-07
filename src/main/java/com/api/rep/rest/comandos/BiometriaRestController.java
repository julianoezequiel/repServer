package com.api.rep.rest.comandos;

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
import com.api.rep.dto.comandos.FPIDDTO;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.rest.ApiRestController;
import com.api.rep.service.ServiceException;
import com.api.rep.service.comandos.biometria.BiometriaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class BiometriaRestController extends ApiRestController {

	@Autowired
	private BiometriaService biometriaService;

	@RequestMapping(value = "/restrict/empregado/bio/{nsu}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receber(@PathVariable("nsu") Integer nsu, @RequestParam("File") MultipartFile uploadFiles)
			throws ServiceException {
		this.biometriaService.receber(uploadFiles, this.getRepAutenticado(), nsu);
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}

	@RequestMapping(value = "/restrict/empregado/bio/{nsu}", produces = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> enviar(@PathVariable("nsu") Integer nsu) throws ServiceException {
		HashMap<String, Object> map = this.biometriaService.enviar(nsu, this.getRepAutenticado());
		return ResponseEntity.ok().contentLength((long) map.get("tamanho"))
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(map.get("arquivo"));
	}

	@RequestMapping(value = "/empregado/bio/{nsu}", produces = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> enviar2(@PathVariable("nsu") Integer nsu) throws ServiceException {
		HashMap<String, Object> map = this.biometriaService.enviar(nsu, null);
		return ResponseEntity.ok().contentLength((long) map.get("tamanho"))
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(map.get("arquivo"));
	}

	@RequestMapping(value = CONSTANTES.URL_LISTA_BIOMETRIA, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receberListaBio(@RequestBody FPIDDTO fpiddto)
			throws ServiceException, JsonProcessingException {
		this.biometriaService.receberListaBio(fpiddto, this.getRepAutenticado());
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}

}
