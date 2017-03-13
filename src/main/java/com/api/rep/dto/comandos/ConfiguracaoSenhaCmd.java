package com.api.rep.dto.comandos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.api.rep.entity.ConfiguracoesSenha;

public class ConfiguracaoSenhaCmd implements Cmd {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	
	private String cfgSCfg;
	private String cfgSPend;
	private String cfgSBio;

	public String getCfgSCfg() {
		return cfgSCfg;
	}

	public void setCfgSCfg(String cfgSCfg) {
		this.cfgSCfg = cfgSCfg;
	}

	public String getCfgSPend() {
		return cfgSPend;
	}

	public void setCfgSPend(String cfgSPend) {
		this.cfgSPend = cfgSPend;
	}

	public String getCfgSBio() {
		return cfgSBio;
	}

	public void setCfgSBio(String cfgSBio) {
		this.cfgSBio = cfgSBio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ConfiguracoesSenha toConfigurcacoesSenha() {
		ConfiguracoesSenha configurcacoesSenha = new ConfiguracoesSenha();
		configurcacoesSenha.setSenhaBio(cfgSBio);
		configurcacoesSenha.setSenhaConfig(cfgSCfg);
		configurcacoesSenha.setSenhaPendrive(cfgSPend);
		return configurcacoesSenha;
	}
}
