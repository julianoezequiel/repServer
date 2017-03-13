package com.api.rep.dto.comandos;

import com.api.rep.entity.ConfiguracoesCartoes;

public class ConfiguracoesCartoesCmd implements Cmd {

	private static final long serialVersionUID = 797764337210228386L;

	private String cfgCMascB;
	private String cfgCMascP;
	private String cfgCDigFixo;
	private String cfgCTpB;
	private String cfgCTpP;

	public synchronized String getCfgCMascB() {
		return cfgCMascB;
	}

	public synchronized void setCfgCMascB(String cfgCMascB) {
		this.cfgCMascB = cfgCMascB;
	}

	public synchronized String getCfgCMascP() {
		return cfgCMascP;
	}

	public synchronized void setCfgCMascP(String cfgCMascP) {
		this.cfgCMascP = cfgCMascP;
	}

	public synchronized String getCfgCDigFixo() {
		return cfgCDigFixo;
	}

	public synchronized void setCfgCDigFixo(String cfgCDigFixo) {
		this.cfgCDigFixo = cfgCDigFixo;
	}

	public synchronized String getCfgCTpB() {
		return cfgCTpB;
	}

	public synchronized void setCfgCTpB(String cfgCTpB) {
		this.cfgCTpB = cfgCTpB;
	}

	public synchronized String getCfgCTpP() {
		return cfgCTpP;
	}

	public synchronized void setCfgCTpP(String cfgCTpP) {
		this.cfgCTpP = cfgCTpP;
	}

	public static synchronized long getSerialversionuid() {
		return serialVersionUID;
	}

	public ConfiguracoesCartoes toConfiguracoesCartoes() {

		ConfiguracoesCartoes cartoes = new ConfiguracoesCartoes();

		cartoes.setDigitosFixo(new Integer(this.cfgCDigFixo));
		cartoes.setMascaraBarras(this.cfgCMascB);
		cartoes.setMascaraProx(this.cfgCMascP);
		cartoes.setTipoBarras(this.cfgCTpB);
		cartoes.setTipoProx(this.cfgCTpP);

		return cartoes;
	}
}
