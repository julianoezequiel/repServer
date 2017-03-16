package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.InfoCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Info {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	private String nomeProduto;
	private String modeloProduto;
	private String numeroRep;
	private String statusBloqueio;
	private String versaoApl;
	private String versaoMrp;
	private Integer numeroUsuario;
	private Integer numeroUsuarioBio;
	private String tipoBio;
	private String modeloBio;
	private Integer capacidadeBio;
	private String statusImpressora;
	private String ultimoNsr;
	
	@JsonIgnore
	@OneToMany(mappedBy = "infoId")
	private Collection<Rep> repCollection;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public synchronized String getNomeProduto() {
		return nomeProduto;
	}

	public synchronized void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public synchronized String getModeloProduto() {
		return modeloProduto;
	}

	public synchronized void setModeloProduto(String modeloProduto) {
		this.modeloProduto = modeloProduto;
	}

	public synchronized String getNumeroRep() {
		return numeroRep;
	}

	public synchronized void setNumeroRep(String numeroRep) {
		this.numeroRep = numeroRep;
	}

	public synchronized String getStatusBloqueio() {
		return statusBloqueio;
	}

	public synchronized void setStatusBloqueio(String statusBloqueio) {
		this.statusBloqueio = statusBloqueio;
	}

	public synchronized String getVersaoApl() {
		return versaoApl;
	}

	public synchronized void setVersaoApl(String versaoApl) {
		this.versaoApl = versaoApl;
	}

	public synchronized String getVersaoMrp() {
		return versaoMrp;
	}

	public synchronized void setVersaoMrp(String versaoMrp) {
		this.versaoMrp = versaoMrp;
	}

	public synchronized Integer getNumeroUsuario() {
		return numeroUsuario;
	}

	public synchronized void setNumeroUsuario(Integer numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}

	public synchronized Integer getNumeroUsuarioBio() {
		return numeroUsuarioBio;
	}

	public synchronized void setNumeroUsuarioBio(Integer numeroUsuarioBio) {
		this.numeroUsuarioBio = numeroUsuarioBio;
	}

	public synchronized String getTipoBio() {
		return tipoBio;
	}

	public synchronized void setTipoBio(String tipoBio) {
		this.tipoBio = tipoBio;
	}

	public synchronized String getModeloBio() {
		return modeloBio;
	}

	public synchronized void setModeloBio(String modeloBio) {
		this.modeloBio = modeloBio;
	}

	public synchronized Integer getCapacidadeBio() {
		return capacidadeBio;
	}

	public synchronized void setCapacidadeBio(Integer capacidadeBio) {
		this.capacidadeBio = capacidadeBio;
	}

	public synchronized String getStatusImpressora() {
		return statusImpressora;
	}

	public synchronized void setStatusImpressora(String statusImpressora) {
		this.statusImpressora = statusImpressora;
	}

	public synchronized String getUltimoNsr() {
		return ultimoNsr;
	}

	public synchronized void setUltimoNsr(String ultimoNsr) {
		this.ultimoNsr = ultimoNsr;
	}

	public InfoCmd toInfoCmd() {
		InfoCmd infoCmd = new InfoCmd();

		infoCmd.setInfCpBio(this.capacidadeBio.toString());
		infoCmd.setInfMBio(this.modeloBio);
		infoCmd.setInfMProd(this.modeloProduto);
		infoCmd.setInfNProd(this.nomeProduto);
		infoCmd.setInfNsr(this.ultimoNsr);
		infoCmd.setInfNumBio(this.numeroUsuarioBio.toString());
		infoCmd.setInfNumFunc(this.numeroUsuario.toString());
		infoCmd.setInfNumRep(this.numeroRep);
		infoCmd.setInfStatImp(this.statusImpressora);
		infoCmd.setInfStatRep(this.statusBloqueio);
		infoCmd.setInfTpBio(this.tipoBio);
		infoCmd.setInfVerApl(this.versaoApl);
		infoCmd.setInfVerMrp(this.versaoMrp);

		return infoCmd;
	}

	/*
	 * INFO_NOME_PRODUTO, INFO_MODELO_PRODUTO, INFO_NUM_REP,
	 * INFO_STATUS_BLOQUEIO, INFO_VERSAO_APL, INFO_VERSAO_MRP,
	 * INFO_NUM_USUARIO", INFO_NUM_BIO, INFO_NSR, INFO_TIPO_BIO,
	 * INFO_MODELO_BIO, INFO_CAPACIDADE_BIO, INFO_STATUS_IMP,
	 */

}
