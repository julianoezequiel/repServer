package com.api.rep.service.comandos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rep.dao.ConfiguracoesCartoesRepository;
import com.api.rep.dao.ConfiguracoesRedeRepository;
import com.api.rep.dao.ConfiguracoesSenhaRepository;
import com.api.rep.dao.HorarioVeraoRepository;
import com.api.rep.dao.RelogioRepository;
import com.api.rep.dto.comandos.ConfiguracaoSenhaCmd;
import com.api.rep.dto.comandos.ConfiguracoesCartoesCmd;
import com.api.rep.dto.comandos.ConfiguracoesRedeCmd;
import com.api.rep.dto.comandos.HorarioVeraoCmd;
import com.api.rep.dto.comandos.RelogioCmd;
import com.api.rep.entity.ConfiguracoesCartoes;
import com.api.rep.entity.ConfiguracoesRede;
import com.api.rep.entity.ConfiguracoesSenha;
import com.api.rep.entity.HorarioVerao;
import com.api.rep.entity.Relogio;
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

	@Autowired
	private RelogioRepository relogioRepository;

	@Autowired
	private HorarioVeraoRepository horarioVeraoRepository;

	public void salvar(ConfiguracaoSenhaCmd configuracaoSenhaCmd, Rep repAutenticado) throws ServiceException {
		repAutenticado = this.getRepPorNumeroSerie(repAutenticado);
		ConfiguracoesSenha configuracoesSenha = configuracaoSenhaCmd.toConfigurcacoesSenha();
		configuracoesSenha.setId(repAutenticado.getConfigurcacoesSenhaId() != null
				? repAutenticado.getConfigurcacoesSenhaId().getId() : null);
		this.configuracoesSenhaRepository.save(configuracoesSenha);
	}

	public void salvar(ConfiguracoesCartoesCmd configuracoesCartoesCmd, Rep repAutenticado) throws ServiceException {
		repAutenticado = this.getRepPorNumeroSerie(repAutenticado);
		ConfiguracoesCartoes configuracoesCartoes = configuracoesCartoesCmd.toConfiguracoesCartoes();
		configuracoesCartoes.setId(repAutenticado.getConfiguracoesCartoesId() != null
				? repAutenticado.getConfiguracoesCartoesId().getDigitosFixo() : null);
		this.configuracoesCartoesRepository.save(configuracoesCartoes);

	}

	public void salvar(ConfiguracoesRedeCmd configuracoesRedeCmd, Rep repAutenticado) throws ServiceException {
		repAutenticado = this.getRepPorNumeroSerie(repAutenticado);
		ConfiguracoesRede configuracoesRede = configuracoesRedeCmd.toConfiguracoesRede();
		configuracoesRede.setId(repAutenticado.getConfiguracoesRedeId() != null
				? repAutenticado.getConfiguracoesRedeId().getId() : null);
		this.configuracoesRedeRepository.save(configuracoesRede);

	}

	public void salvar(RelogioCmd relogioCmd, Rep repAutenticado) throws ServiceException {
		repAutenticado = this.getRepPorNumeroSerie(repAutenticado);
		Relogio relogio = relogioCmd.toRelogio();
		relogio.setId(repAutenticado.getRelogioId() != null ? repAutenticado.getRelogioId().getId() : null);
		this.relogioRepository.save(relogio);

	}

	public void salvar(HorarioVeraoCmd horarioVeraoCmd, Rep repAutenticado) throws ServiceException {
		repAutenticado = this.getRepPorNumeroSerie(repAutenticado);
		HorarioVerao horarioVerao = horarioVeraoCmd.toHorarioVerao();
		horarioVerao
				.setId(repAutenticado.getHorarioVeraoId() != null ? repAutenticado.getHorarioVeraoId().getId() : null);
		this.horarioVeraoRepository.save(horarioVerao);
	}

}
