package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.Cmd;
import com.api.rep.dto.comandos.ConfiguracaoSenhaCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ConfiguracoesSenha {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@JsonIgnore
	@OneToMany(mappedBy = "configurcacoesSenhaId")
	private Collection<Tarefa> tarefaCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "configurcacoesSenhaId")
	private Collection<Rep> repCollection;

	private String senhaConfig;
	private String senhaPendrive;
	private String senhaBio;

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized Collection<Tarefa> getTarefaCollection() {
		return tarefaCollection;
	}

	public synchronized void setTarefaCollection(Collection<Tarefa> tarefaCollection) {
		this.tarefaCollection = tarefaCollection;
	}

	public synchronized String getSenhaConfig() {
		return senhaConfig;
	}

	public synchronized void setSenhaConfig(String senhaConfig) {
		this.senhaConfig = senhaConfig;
	}

	public synchronized String getSenhaPendrive() {
		return senhaPendrive;
	}

	public synchronized void setSenhaPendrive(String senhaPendrive) {
		this.senhaPendrive = senhaPendrive;
	}

	public synchronized String getSenhaBio() {
		return senhaBio;
	}

	public synchronized void setSenhaBio(String senhaBio) {
		this.senhaBio = senhaBio;
	}

	public synchronized Collection<Rep> getRepCollection() {
		return repCollection;
	}

	public synchronized void setRepCollection(Collection<Rep> repCollection) {
		this.repCollection = repCollection;
	}

	public ConfiguracaoSenhaCmd toConfiguracaoCmd() {
		ConfiguracaoSenhaCmd configuracaoSenhaCmd = new ConfiguracaoSenhaCmd();
		configuracaoSenhaCmd.setCfgSBio(senhaBio);
		configuracaoSenhaCmd.setCfgSCfg(senhaConfig);
		configuracaoSenhaCmd.setCfgSPend(senhaPendrive);
		return configuracaoSenhaCmd;
	}

}
