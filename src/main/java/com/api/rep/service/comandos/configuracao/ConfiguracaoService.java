package com.api.rep.service.comandos.configuracao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.dao.ConfiguracaoRepository;
import com.api.rep.entity.Configuracao;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;

@Service
public class ConfiguracaoService extends ApiService {

	@Autowired
	private ConfiguracaoRepository configuracaoRepository;

	public Collection<Configuracao> listar() {
		return this.configuracaoRepository.findAll();
	}

	public Configuracao salvar(Configuracao configuracao, Rep repAutenticado) {
		return this.configuracaoRepository.save(configuracao);
	}

	public String excluirPorId(Integer id) throws ServiceException {

		Configuracao configuracao = buscaPorId(id);
		if (configuracao != null) {
			this.configuracaoRepository.delete(configuracao);
			return "Configuração excluida com sucesso";
		}
		throw new ServiceException(HttpStatus.NO_CONTENT, "Configuracao não encontrada");
	}

	public Configuracao buscaPorId(Integer id) {
		return this.configuracaoRepository.findOne(id);
	}



}
