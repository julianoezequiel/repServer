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
import com.api.rep.dto.comandos.EmpregadoDTO;
import com.api.rep.entity.Empregado;
import com.api.rep.rest.ApiRestController;
import com.api.rep.service.ServiceException;
import com.api.rep.service.comandos.empregado.EmpregadoService;

@RestController
@RequestMapping(value = CONSTANTES.URL_EMPREGADO)
public class EmpregadoRestController extends ApiRestController {

	@Autowired
	private EmpregadoService empregadoService;

	// TODO: Converter os objetos empregado em EmpregadoDTO

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Collection<Empregado>> buscarTodos() {
		return new ResponseEntity<Collection<Empregado>>(this.empregadoService.listar(), HttpStatus.OK);
	}

	@RequestMapping(value = "cadastro", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Empregado> salvar(@RequestBody Empregado empregado) throws ServiceException {
		return new ResponseEntity<Empregado>(this.empregadoService.salvar(empregado, this.getRepAutenticado()),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "cadastro/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<String> excluir(@PathVariable Integer id) throws ServiceException {
		return new ResponseEntity<String>(this.empregadoService.excluirPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> receber(@RequestBody EmpregadoDTO empregadoDTO) throws ServiceException {
		this.empregadoService.receber(empregadoDTO, this.getRepAutenticado());
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
