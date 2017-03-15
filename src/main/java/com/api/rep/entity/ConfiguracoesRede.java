package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.ConfiguracoesRedeCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ConfiguracoesRede {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@JsonIgnore
	@OneToMany(mappedBy = "configuracoesRedeId")
	private Collection<Tarefa> tarefaCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "configuracoesRedeId")
	private Collection<Rep> repCollection;

	private Integer[] ipRep;
	private Integer portaRep;
	private Integer[] numeroMac;
	private String nomeRep;
	private Integer repInicia;
	private Integer[] ipServidor;
	private Integer portaServidor;
	private Integer[] mascaraRede;
	private Integer[] gateway;
	private Integer intervaloCom;
	private Integer habilitaDns;
	private String nomeHost;
	private Integer[] ipDns;
	private Integer habilitaDhcp;

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

	public synchronized Integer[] getIpRep() {
		return ipRep;
	}

	public synchronized void setIpRep(Integer[] ipRep) {
		this.ipRep = ipRep;
	}

	public synchronized Integer getPortaRep() {
		return portaRep;
	}

	public synchronized void setPortaRep(Integer portaRep) {
		this.portaRep = portaRep;
	}

	public synchronized Integer[] getNumeroMac() {
		return numeroMac;
	}

	public synchronized void setNumeroMac(Integer[] numeroMac) {
		this.numeroMac = numeroMac;
	}

	
	public synchronized String getNomeRep() {
		return nomeRep;
	}

	public synchronized void setNomeRep(String nomeRep) {
		this.nomeRep = nomeRep;
	}

	public synchronized Integer getRepInicia() {
		return repInicia;
	}

	public synchronized void setRepInicia(Integer repInicia) {
		this.repInicia = repInicia;
	}

	public synchronized Integer[] getIpServidor() {
		return ipServidor;
	}

	public synchronized void setIpServidor(Integer[] ipServidor) {
		this.ipServidor = ipServidor;
	}

	public synchronized Integer getPortaServidor() {
		return portaServidor;
	}

	public synchronized void setPortaServidor(Integer portaServidor) {
		this.portaServidor = portaServidor;
	}

	public synchronized Integer[] getMascaraRede() {
		return mascaraRede;
	}

	public synchronized void setMascaraRede(Integer[] mascaraRede) {
		this.mascaraRede = mascaraRede;
	}

	public synchronized Integer[] getGateway() {
		return gateway;
	}

	public synchronized void setGateway(Integer[] gateway) {
		this.gateway = gateway;
	}

	public synchronized Integer getIntervaloCom() {
		return intervaloCom;
	}

	public synchronized void setIntervaloCom(Integer intervaloCom) {
		this.intervaloCom = intervaloCom;
	}

	public synchronized Integer getHabilitaDns() {
		return habilitaDns;
	}

	public synchronized void setHabilitaDns(Integer habilitaDns) {
		this.habilitaDns = habilitaDns;
	}

	public synchronized String getNomeHost() {
		return nomeHost;
	}

	public synchronized void setNomeHost(String nomeHost) {
		this.nomeHost = nomeHost;
	}

	public synchronized Integer[] getIpDns() {
		return ipDns;
	}

	public synchronized void setIpDns(Integer[] ipDns) {
		this.ipDns = ipDns;
	}

	public synchronized Integer getHabilitaDhcp() {
		return habilitaDhcp;
	}

	public synchronized void setHabilitaDhcp(Integer habilitaDhcp) {
		this.habilitaDhcp = habilitaDhcp;
	}

	public ConfiguracoesRedeCmd toConfiguracoesRedeCmd() {
		ConfiguracoesRedeCmd configuracoesRedeCmd = new ConfiguracoesRedeCmd();

		configuracoesRedeCmd.setCfgRDhcp(this.habilitaDhcp);
		configuracoesRedeCmd.setCfgRGat(this.gateway);
		configuracoesRedeCmd.setCfgRHabDns(this.habilitaDhcp);
		configuracoesRedeCmd.setCfgRIntCom(this.intervaloCom);
		configuracoesRedeCmd.setCfgRIpDns(this.ipDns);
		configuracoesRedeCmd.setCfgRIpRep(this.ipRep);
		configuracoesRedeCmd.setCfgRIpServ(this.ipServidor);
		configuracoesRedeCmd.setCfgRMasc(this.mascaraRede);
		configuracoesRedeCmd.setCfgRNHost(this.nomeHost);
		configuracoesRedeCmd.setCfgRNRep(this.nomeRep);
		configuracoesRedeCmd.setCfgRNumMac(this.numeroMac);
		configuracoesRedeCmd.setCfgRPortRep(this.portaRep);
		configuracoesRedeCmd.setCfgRPortServ(this.portaServidor);
		configuracoesRedeCmd.setCfgRRepIn(this.repInicia);

		return configuracoesRedeCmd;
	}
}
