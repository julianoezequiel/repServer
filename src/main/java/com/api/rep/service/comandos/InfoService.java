package com.api.rep.service.comandos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rep.dao.InfoRepository;
import com.api.rep.dto.comandos.InfoCmd;
import com.api.rep.entity.Info;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;

@Service
public class InfoService extends ApiService {

	@Autowired
	private InfoRepository infoRepository;

	public void salvar(InfoCmd infoCmd, Rep repAutenticado) throws ServiceException {

		repAutenticado = this.getRepPorNumeroSerie(repAutenticado);

		Info info = infoCmd.toInfo();
		info.setId(repAutenticado.getInfoId() != null ? repAutenticado.getInfoId().getId() : null);

		this.infoRepository.save(info);

	}
}
