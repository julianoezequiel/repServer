package com.api.rep.rest.comandos;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comandos.ConfiguracaoSenhaCmd;
import com.api.rep.dto.comandos.ConfiguracoesCartoesCmd;
import com.api.rep.dto.comandos.ConfiguracoesRedeCmd;
import com.api.rep.dto.comandos.InfoCmd;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.rest.ApiRestController;
import com.api.rep.service.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ConfigurcacoesRestController extends ApiRestController {

	@RequestMapping(value = CONSTANTES.URL_CONFIG_SENHA, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receberListaBio(@RequestBody ConfiguracaoSenhaCmd configuracaoSenhaCmd)
			throws ServiceException, JsonProcessingException {
		LOGGER.info("Senhas Recebidas : " + this.getMapper().writeValueAsString(configuracaoSenhaCmd));
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}

	@RequestMapping(value = CONSTANTES.URL_INFO, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receberListaBio(@RequestBody InfoCmd infoCmd)
			throws ServiceException, JsonProcessingException {
		LOGGER.info("Senhas Recebidas : " + this.getMapper().writeValueAsString(infoCmd));
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}

	@RequestMapping(value = CONSTANTES.URL_CONFIG_CARTOES, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receberCartoes(@RequestBody ConfiguracoesCartoesCmd configuracoesCartoesCmd)
			throws ServiceException, JsonProcessingException {
		LOGGER.info("Senhas Recebidas : " + this.getMapper().writeValueAsString(configuracoesCartoesCmd));
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}

	@RequestMapping(value = CONSTANTES.URL_CONFIG_REDE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receberCartoes(@RequestBody ConfiguracoesRedeCmd configuracoesRedeCmd)
			throws ServiceException, JsonProcessingException {
		LOGGER.info("Senhas Recebidas : " + this.getMapper().writeValueAsString(configuracoesRedeCmd));
		return new ResponseEntity<RespostaSevidorDTO>(HttpStatus.OK);
	}
}
