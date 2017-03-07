package com.api.rep.rest.comandos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(value = CONSTANTES.URL_BIOMETRIA, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receber(RequestEntity<InputStreamResource> entity) throws ServiceException {
		this.biometriaService.receber(entity, this.getRepAutenticado());
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}

	@RequestMapping(value = CONSTANTES.URL_LISTA_BIOMETRIA, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receberListaBio(@RequestBody FPIDDTO fpiddto)
			throws ServiceException, JsonProcessingException {
		this.biometriaService.receberListaBio(fpiddto, this.getRepAutenticado());
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}

}
