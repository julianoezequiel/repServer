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

	private String ipRep;
	private String portaRep;
	private String numeroMac;
	private String nomeRede;
	private String nomeRep;
	private Boolean repInicia;
	private String ipServidor;
	private String portaServidor;
	private String mascaraRede;
	private String gateway;
	private String intervaloCom;
	private Boolean habilitaDns;
	private String nomeHost;
	private String ipDns;
	private String chaveCom;
	private Boolean habilitaDhcp;

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

	public synchronized String getIpRep() {
		return ipRep;
	}

	public synchronized void setIpRep(String ipRep) {
		this.ipRep = ipRep;
	}

	public synchronized String getPortaRep() {
		return portaRep;
	}

	public synchronized void setPortaRep(String portaRep) {
		this.portaRep = portaRep;
	}

	public synchronized String getNumeroMac() {
		return numeroMac;
	}

	public synchronized void setNumeroMac(String numeroMac) {
		this.numeroMac = numeroMac;
	}

	public synchronized String getNomeRede() {
		return nomeRede;
	}

	public synchronized void setNomeRede(String nomeRede) {
		this.nomeRede = nomeRede;
	}

	public synchronized String getNomeRep() {
		return nomeRep;
	}

	public synchronized void setNomeRep(String nomeRep) {
		this.nomeRep = nomeRep;
	}

	public synchronized Boolean getRepInicia() {
		return repInicia;
	}

	public synchronized void setRepInicia(Boolean repInicia) {
		this.repInicia = repInicia;
	}

	public synchronized String getIpServidor() {
		return ipServidor;
	}

	public synchronized void setIpServidor(String ipServidor) {
		this.ipServidor = ipServidor;
	}

	public synchronized String getPortaServidor() {
		return portaServidor;
	}

	public synchronized void setPortaServidor(String portaServidor) {
		this.portaServidor = portaServidor;
	}

	public synchronized String getMascaraRede() {
		return mascaraRede;
	}

	public synchronized void setMascaraRede(String mascaraRede) {
		this.mascaraRede = mascaraRede;
	}

	public synchronized String getGateway() {
		return gateway;
	}

	public synchronized void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public synchronized String getIntervaloCom() {
		return intervaloCom;
	}

	public synchronized void setIntervaloCom(String intervaloCom) {
		this.intervaloCom = intervaloCom;
	}

	public synchronized Boolean getHabilitaDns() {
		return habilitaDns;
	}

	public synchronized void setHabilitaDns(Boolean habilitaDns) {
		this.habilitaDns = habilitaDns;
	}

	public synchronized String getNomeHost() {
		return nomeHost;
	}

	public synchronized void setNomeHost(String nomeHost) {
		this.nomeHost = nomeHost;
	}

	public synchronized String getIpDns() {
		return ipDns;
	}

	public synchronized void setIpDns(String ipDns) {
		this.ipDns = ipDns;
	}

	public synchronized String getChaveCom() {
		return chaveCom;
	}

	public synchronized void setChaveCom(String chaveCom) {
		this.chaveCom = chaveCom;
	}

	public synchronized Boolean getHabilitaDhcp() {
		return habilitaDhcp;
	}

	public synchronized void setHabilitaDhcp(Boolean habilitaDhcp) {
		this.habilitaDhcp = habilitaDhcp;
	}

	public ConfiguracoesRedeCmd toConfiguracoesRedeCmd() {
		ConfiguracoesRedeCmd configuracoesRedeCmd = new ConfiguracoesRedeCmd();

		configuracoesRedeCmd.setCfgRChaveCom(this.chaveCom);
		configuracoesRedeCmd.setCfgRDhcp(this.habilitaDhcp.toString());
		configuracoesRedeCmd.setCfgRGat(this.gateway);
		configuracoesRedeCmd.setCfgRHabDns(this.habilitaDhcp.toString());
		configuracoesRedeCmd.setCfgRIntCom(this.intervaloCom);
		configuracoesRedeCmd.setCfgRIpDns(this.ipDns);
		configuracoesRedeCmd.setCfgRIpRep(this.ipRep);
		configuracoesRedeCmd.setCfgRIpServ(this.ipServidor);
		configuracoesRedeCmd.setCfgRMasc(this.mascaraRede);
		configuracoesRedeCmd.setCfgRNHost(this.nomeHost);
		configuracoesRedeCmd.setCfgRNRede(this.nomeRede);
		configuracoesRedeCmd.setCfgRNRep(this.nomeRep);
		configuracoesRedeCmd.setCfgRNumMac(this.numeroMac);
		configuracoesRedeCmd.setCfgRPortRep(this.portaRep);
		configuracoesRedeCmd.setCfgRPortServ(this.portaServidor);
		configuracoesRedeCmd.setCfgRRepIn(this.repInicia.toString());

		return configuracoesRedeCmd;
	}
}
