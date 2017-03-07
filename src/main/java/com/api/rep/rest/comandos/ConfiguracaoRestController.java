package com.api.rep.rest.comandos;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comandos.ConfiguracaoDTO;
import com.api.rep.entity.Configuracao;
import com.api.rep.rest.ApiRestController;
import com.api.rep.service.ServiceException;
import com.api.rep.service.comandos.configuracao.ConfiguracaoService;

@RestController
@RequestMapping(value = CONSTANTES.URL_CONFIG)
public class ConfiguracaoRestController extends ApiRestController {

	@Autowired
	private ConfiguracaoService configuracaoService;

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Collection<Configuracao>> Listar() {
		return new ResponseEntity<Collection<Configuracao>>(this.configuracaoService.listar(), HttpStatus.OK);
	}

	@RequestMapping(value = "cadastro", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Configuracao> salvar(@RequestBody Configuracao configuracao) throws ServiceException {
		return new ResponseEntity<Configuracao>(this.configuracaoService.salvar(configuracao, this.getRepAutenticado()),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "cadastro/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<String> excluir(@PathVariable Integer id) throws ServiceException {
		return new ResponseEntity<String>(this.configuracaoService.excluirPorId(id), HttpStatus.OK);
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<?> receber(@RequestBody ConfiguracaoDTO configuracaoDTO) throws ServiceException {
		this.configuracaoService.receber(configuracaoDTO, this.getRepAutenticado());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
