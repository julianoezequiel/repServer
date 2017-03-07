package com.api.rep.dto.comandos;

public class ConfiguracaoDTO extends ComandoAbstract{

	private static final long serialVersionUID = 797764337210228386L;
	
	private ConfiguracoesRede configuracoesRede;

	public synchronized ConfiguracoesRede getConfiguracoesRede() {
		return configuracoesRede;
	}

	public synchronized void setConfiguracoesRede(ConfiguracoesRede configuracoesRede) {
		this.configuracoesRede = configuracoesRede;
	}
	
	

}
