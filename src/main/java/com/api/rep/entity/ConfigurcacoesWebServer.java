package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.ConfiguracacoesWebServerCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ConfigurcacoesWebServer {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	private Integer tipoConfig;
	private Integer[] ipSeguro;

	@JsonIgnore
	@OneToMany(mappedBy = "configurcacoesWebServerId")
	private Collection<Tarefa> tarefaCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "configurcacoesWebServerId")
	private Collection<Rep> repCollection;

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

	public synchronized Collection<Rep> getRepCollection() {
		return repCollection;
	}

	public synchronized void setRepCollection(Collection<Rep> repCollection) {
		this.repCollection = repCollection;
	}

	public synchronized Integer getTipoConfig() {
		return tipoConfig;
	}

	public synchronized void setTipoConfig(Integer tipoConfig) {
		this.tipoConfig = tipoConfig;
	}

	public synchronized Integer[] getIpSeguro() {
		return ipSeguro;
	}

	public synchronized void setIpSeguro(Integer[] ipSeguro) {
		this.ipSeguro = ipSeguro;
	}

	public ConfiguracacoesWebServerCmd toConfigurcacoesWebServer() {
		ConfiguracacoesWebServerCmd cmd = new ConfiguracacoesWebServerCmd();
		cmd.setCfgWIPS(ipSeguro);
		cmd.setCfgWTpCfg(tipoConfig);
		return cmd;
	}

}
