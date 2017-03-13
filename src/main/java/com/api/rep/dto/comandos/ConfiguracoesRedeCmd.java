package com.api.rep.dto.comandos;

import com.api.rep.entity.ConfiguracoesRede;

public class ConfiguracoesRedeCmd implements Cmd {

	private static final long serialVersionUID = 1L;

	private String cfgRIpRep;
	private String cfgRDhcp;
	private String cfgRPortRep;
	private String cfgRNumMac;
	private String cfgRNRede;
	private String cfgRNRep;
	private String cfgRRepIn;
	private String cfgRIpServ;
	private String cfgRPortServ;
	private String cfgRMasc;
	private String cfgRGat;
	private String cfgRIntCom;
	private String cfgRHabDns;
	private String cfgRNHost;
	private String cfgRIpDns;
	private String cfgRChaveCom;

	public synchronized String getCfgRIpRep() {
		return cfgRIpRep;
	}

	public synchronized void setCfgRIpRep(String cfgRIpRep) {
		this.cfgRIpRep = cfgRIpRep;
	}

	public synchronized String getCfgRDhcp() {
		return cfgRDhcp;
	}

	public synchronized void setCfgRDhcp(String cfgRDhcp) {
		this.cfgRDhcp = cfgRDhcp;
	}

	public synchronized String getCfgRPortRep() {
		return cfgRPortRep;
	}

	public synchronized void setCfgRPortRep(String cfgRPortRep) {
		this.cfgRPortRep = cfgRPortRep;
	}

	public synchronized String getCfgRNumMac() {
		return cfgRNumMac;
	}

	public synchronized void setCfgRNumMac(String cfgRNumMac) {
		this.cfgRNumMac = cfgRNumMac;
	}

	public synchronized String getCfgRNRede() {
		return cfgRNRede;
	}

	public synchronized void setCfgRNRede(String cfgRNRede) {
		this.cfgRNRede = cfgRNRede;
	}

	public synchronized String getCfgRNRep() {
		return cfgRNRep;
	}

	public synchronized void setCfgRNRep(String cfgRNRep) {
		this.cfgRNRep = cfgRNRep;
	}

	public synchronized String getCfgRRepIn() {
		return cfgRRepIn;
	}

	public synchronized void setCfgRRepIn(String cfgRRepIn) {
		this.cfgRRepIn = cfgRRepIn;
	}

	public synchronized String getCfgRIpServ() {
		return cfgRIpServ;
	}

	public synchronized void setCfgRIpServ(String cfgRIpServ) {
		this.cfgRIpServ = cfgRIpServ;
	}

	public synchronized String getCfgRPortServ() {
		return cfgRPortServ;
	}

	public synchronized void setCfgRPortServ(String cfgRPortServ) {
		this.cfgRPortServ = cfgRPortServ;
	}

	public synchronized String getCfgRMasc() {
		return cfgRMasc;
	}

	public synchronized void setCfgRMasc(String cfgRMasc) {
		this.cfgRMasc = cfgRMasc;
	}

	public synchronized String getCfgRGat() {
		return cfgRGat;
	}

	public synchronized void setCfgRGat(String cfgRGat) {
		this.cfgRGat = cfgRGat;
	}

	public synchronized String getCfgRIntCom() {
		return cfgRIntCom;
	}

	public synchronized void setCfgRIntCom(String cfgRIntCom) {
		this.cfgRIntCom = cfgRIntCom;
	}

	public synchronized String getCfgRHabDns() {
		return cfgRHabDns;
	}

	public synchronized void setCfgRHabDns(String cfgRHabDns) {
		this.cfgRHabDns = cfgRHabDns;
	}

	public synchronized String getCfgRNHost() {
		return cfgRNHost;
	}

	public synchronized void setCfgRNHost(String cfgRNHost) {
		this.cfgRNHost = cfgRNHost;
	}

	public synchronized String getCfgRIpDns() {
		return cfgRIpDns;
	}

	public synchronized void setCfgRIpDns(String cfgRIpDns) {
		this.cfgRIpDns = cfgRIpDns;
	}

	public synchronized String getCfgRChaveCom() {
		return cfgRChaveCom;
	}

	public synchronized void setCfgRChaveCom(String cfgRChaveCom) {
		this.cfgRChaveCom = cfgRChaveCom;
	}

	public static synchronized long getSerialversionuid() {
		return serialVersionUID;
	}

	public ConfiguracoesRede toConfiguracoesRede() {
		ConfiguracoesRede configuracoesRede = new ConfiguracoesRede();
		configuracoesRede.setChaveCom(this.cfgRChaveCom);
		configuracoesRede.setGateway(this.cfgRGat);
		configuracoesRede.setHabilitaDhcp(Boolean.parseBoolean(this.cfgRDhcp));
		configuracoesRede.setHabilitaDns(Boolean.parseBoolean(this.cfgRHabDns));
		configuracoesRede.setIntervaloCom(this.cfgRIntCom);
		configuracoesRede.setIpDns(this.cfgRIpDns);
		configuracoesRede.setIpRep(this.cfgRIpRep);
		configuracoesRede.setIpServidor(this.cfgRIpServ);
		configuracoesRede.setMascaraRede(this.cfgRMasc);
		configuracoesRede.setNomeHost(this.cfgRNHost);
		configuracoesRede.setNomeRede(this.cfgRNRede);
		configuracoesRede.setNomeRep(this.cfgRNRep);
		configuracoesRede.setNumeroMac(this.cfgRNumMac);
		configuracoesRede.setPortaRep(this.cfgRPortRep);
		configuracoesRede.setPortaServidor(this.cfgRPortServ);
		configuracoesRede.setRepInicia(Boolean.parseBoolean(this.cfgRRepIn));

		return configuracoesRede;
	}

}
