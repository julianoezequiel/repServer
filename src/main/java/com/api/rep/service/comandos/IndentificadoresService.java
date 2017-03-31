package com.api.rep.service.comandos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rep.dao.IdentificadoresRepository;
import com.api.rep.dto.comandos.IdentificadoresCmd;
import com.api.rep.entity.Identificadores;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;

@Service
public class IndentificadoresService extends ApiService {

	@Autowired
	private IdentificadoresRepository identificadoresRepository;

	public void salvar(IdentificadoresCmd identificadoresCmd, Rep repAutenticado) throws ServiceException {

		repAutenticado = this.getRepService().buscarPorNumeroSerie(repAutenticado);

		Identificadores identificadores = identificadoresCmd.toIdentificadores();
		identificadores.setId(
				repAutenticado.getIdentificadoresId() != null ? repAutenticado.getIdentificadoresId().getId() : null);
		this.identificadoresRepository.save(identificadores);

	}

}
