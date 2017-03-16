package com.api.rep.service.comandos;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.dao.ConfiguracoesCartoesRepository;
import com.api.rep.dao.ConfiguracoesRedeRepository;
import com.api.rep.dao.ConfiguracoesSenhaRepository;
import com.api.rep.entity.ConfiguracoesRede;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;

@Service
public class ConfiguracaoService extends ApiService {

	@Autowired
	private ConfiguracoesRedeRepository configuracoesRedeRepository;

	@Autowired
	private ConfiguracoesCartoesRepository configuracoesCartoesRepository;

	@Autowired
	private ConfiguracoesSenhaRepository configuracoesSenhaRepository;

	public Collection<ConfiguracoesRede> listar() {
		return this.configuracoesRedeRepository.findAll();
	}

	public ConfiguracoesRede salvar(ConfiguracoesRede configuracoesRede, Rep repAutenticado) {
		return this.configuracoesRedeRepository.save(configuracoesRede);
	}

	public String excluirPorId(Integer id) throws ServiceException {

		ConfiguracoesRede configuracoesRede = buscaPorId(id);
		if (configuracoesRede != null) {
			this.configuracoesRedeRepository.delete(configuracoesRede);
			return "Configuração excluida com sucesso";
		}
		throw new ServiceException(HttpStatus.NO_CONTENT, "ConfiguracoesRede não encontrada");
	}

	public ConfiguracoesRede buscaPorId(Integer id) {
		return this.configuracoesRedeRepository.findOne(id);
	}

}
