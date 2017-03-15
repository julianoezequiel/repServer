package com.api.rep.dto.comandos;

import com.api.rep.entity.ConfiguracoesCartoes;

public class ConfiguracoesCartoesCmd implements Cmd {

	private static final long serialVersionUID = 797764337210228386L;

	private Integer[] cfgCMascB;
	private Integer[] cfgCMascP;
	private Integer cfgCDigFixo;
	private Integer cfgCTpB;
	private Integer cfgCTpP;

	public synchronized Integer[] getCfgCMascB() {
		return cfgCMascB;
	}

	public synchronized void setCfgCMascB(Integer[] cfgCMascB) {
		this.cfgCMascB = cfgCMascB;
	}

	public synchronized Integer[] getCfgCMascP() {
		return cfgCMascP;
	}

	public synchronized void setCfgCMascP(Integer[] cfgCMascP) {
		this.cfgCMascP = cfgCMascP;
	}

	public synchronized Integer getCfgCDigFixo() {
		return cfgCDigFixo;
	}

	public synchronized void setCfgCDigFixo(Integer cfgCDigFixo) {
		this.cfgCDigFixo = cfgCDigFixo;
	}

	public synchronized Integer getCfgCTpB() {
		return cfgCTpB;
	}

	public synchronized void setCfgCTpB(Integer cfgCTpB) {
		this.cfgCTpB = cfgCTpB;
	}

	public synchronized Integer getCfgCTpP() {
		return cfgCTpP;
	}

	public synchronized void setCfgCTpP(Integer cfgCTpP) {
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
