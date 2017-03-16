package com.api.rep.service.comandos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rep.dao.IndentificadoresRepository;
import com.api.rep.service.ApiService;

@Service
public class IndentificadoresService extends ApiService {

	@Autowired
	private IndentificadoresRepository indentificadoresRepository;

}
