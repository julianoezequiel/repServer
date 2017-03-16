package com.api.rep.service.comandos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rep.dao.InfoRepository;
import com.api.rep.service.ApiService;

@Service
public class InfoService extends ApiService {

	@Autowired
	private InfoRepository infoRepository;
}
