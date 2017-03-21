package com.api.rep.dto.comandos;

import com.api.rep.entity.ConfigurcacoesWebServer;

public class ConfiguracacoesWebServerCmd implements Cmd {

	private static final long serialVersionUID = 1L;

	private Integer cfgWTpCfg;
	private Integer[] cfgWIPS;

	public synchronized Integer getCfgWTpCfg() {
		return cfgWTpCfg;
	}

	public synchronized void setCfgWTpCfg(Integer cfgWTpCfg) {
		this.cfgWTpCfg = cfgWTpCfg;
	}

	public synchronized Integer[] getCfgWIPS() {
		return cfgWIPS;
	}

	public synchronized void setCfgWIPS(Integer[] cfgWIPS) {
		this.cfgWIPS = cfgWIPS;
	}

	public static synchronized long getSerialversionuid() {
		return serialVersionUID;
	}

	public ConfigurcacoesWebServer toConfigurcacoesWebServer() {
		ConfigurcacoesWebServer configurcacoesWebServer = new ConfigurcacoesWebServer();
		configurcacoesWebServer.setIpSeguro(cfgWIPS);
		configurcacoesWebServer.setTipoConfig(cfgWTpCfg);
		return configurcacoesWebServer;
	}

}
