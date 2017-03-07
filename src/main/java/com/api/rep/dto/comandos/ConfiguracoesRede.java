package com.api.rep.dto.comandos;

import java.io.Serializable;

public class ConfiguracoesRede implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gateway;
	private Boolean habilitaDns;
	private String intervaloCom;
	private String ipDns;
	private String ipRep;
	private String ipServidor;
	private String mascaraRede;
	private String nomeHost = "cloud.topdata.com.br";
	private String nomeRede;
	private String numMac;
	private String portaRep; 
	private String portaServidor;
	private Boolean repInicia;
	public synchronized String getGateway() {
		return gateway;
	}
	public synchronized void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public synchronized Boolean getHabilitaDns() {
		return habilitaDns;
	}
	public synchronized void setHabilitaDns(Boolean habilitaDns) {
		this.habilitaDns = habilitaDns;
	}
	public synchronized String getIntervaloCom() {
		return intervaloCom;
	}
	public synchronized void setIntervaloCom(String intervaloCom) {
		this.intervaloCom = intervaloCom;
	}
	public synchronized String getIpDns() {
		return ipDns;
	}
	public synchronized void setIpDns(String ipDns) {
		this.ipDns = ipDns;
	}
	public synchronized String getIpRep() {
		return ipRep;
	}
	public synchronized void setIpRep(String ipRep) {
		this.ipRep = ipRep;
	}
	public synchronized String getIpServidor() {
		return ipServidor;
	}
	public synchronized void setIpServidor(String ipServidor) {
		this.ipServidor = ipServidor;
	}
	public synchronized String getMascaraRede() {
		return mascaraRede;
	}
	public synchronized void setMascaraRede(String mascaraRede) {
		this.mascaraRede = mascaraRede;
	}
	public synchronized String getNomeHost() {
		return nomeHost;
	}
	public synchronized void setNomeHost(String nomeHost) {
		this.nomeHost = nomeHost;
	}
	public synchronized String getNomeRede() {
		return nomeRede;
	}
	public synchronized void setNomeRede(String nomeRede) {
		this.nomeRede = nomeRede;
	}
	public synchronized String getNumMac() {
		return numMac;
	}
	public synchronized void setNumMac(String numMac) {
		this.numMac = numMac;
	}
	public synchronized String getPortaRep() {
		return portaRep;
	}
	public synchronized void setPortaRep(String portaRep) {
		this.portaRep = portaRep;
	}
	public synchronized String getPortaServidor() {
		return portaServidor;
	}
	public synchronized void setPortaServidor(String portaServidor) {
		this.portaServidor = portaServidor;
	}
	public synchronized Boolean getRepInicia() {
		return repInicia;
	}
	public synchronized void setRepInicia(Boolean repInicia) {
		this.repInicia = repInicia;
	}
	
	
	
}
