package com.api.rep.rest.rep;

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

import com.api.rep.dto.comunicacao.RepDTO;
import com.api.rep.service.ServiceException;
import com.api.rep.service.rep.RepService;

@RestController
@RequestMapping(value = "rep")
public class RepRestController {

	@Autowired
	private RepService repService;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Collection<RepDTO>> buscarTodos() {
		return new ResponseEntity<Collection<RepDTO>>(this.repService.buscarTodos(), HttpStatus.OK);
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<RepDTO> salvar(@RequestBody RepDTO repDTO) throws ServiceException{
		return new ResponseEntity<RepDTO>(this.repService.salvar(repDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{id}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<String> excluir(@PathVariable Integer id) throws ServiceException{
		return new ResponseEntity<String>(this.repService.excluir(id),HttpStatus.OK);
	}
}
