package com.api.rep.rest.comandos;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comandos.IdentificadoresCmd;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.rest.ApiRestController;
import com.api.rep.service.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class IdentificadoresRestController extends ApiRestController {

	@RequestMapping(value = CONSTANTES.URL_IDENTFICACAO, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receberConfigIdentificadores(@RequestBody IdentificadoresCmd identificadoresCmd)
			throws ServiceException, JsonProcessingException {
		LOGGER.info("Identificadores : " + this.getMapper().writeValueAsString(identificadoresCmd));
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}
}
