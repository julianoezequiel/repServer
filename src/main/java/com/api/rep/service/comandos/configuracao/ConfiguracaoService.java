package com.api.rep.service.comandos.configuracao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.dao.ConfiguracaoRepository;
import com.api.rep.entity.ConfiguracoesRede;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;

@Service
public class ConfiguracaoService extends ApiService {

	@Autowired
	private ConfiguracaoRepository configuracaoRepository;

	public Collection<ConfiguracoesRede> listar() {
		return this.configuracaoRepository.findAll();
	}

	public ConfiguracoesRede salvar(ConfiguracoesRede configuracoesRede, Rep repAutenticado) {
		return this.configuracaoRepository.save(configuracoesRede);
	}

	public String excluirPorId(Integer id) throws ServiceException {

		ConfiguracoesRede configuracoesRede = buscaPorId(id);
		if (configuracoesRede != null) {
			this.configuracaoRepository.delete(configuracoesRede);
			return "Configuração excluida com sucesso";
		}
		throw new ServiceException(HttpStatus.NO_CONTENT, "ConfiguracoesRede não encontrada");
	}

	public ConfiguracoesRede buscaPorId(Integer id) {
		return this.configuracaoRepository.findOne(id);
	}



}
