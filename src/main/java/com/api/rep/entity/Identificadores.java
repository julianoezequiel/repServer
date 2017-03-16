package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Identificadores {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	private String versaoApl;
	private String idApr;
	private String versaoMrp;
	private String idMrp;
	private String versaoTamper;
	private String versaoBoot;
	private String versaoBio;
	private String versaoProx;
	private String versaoImp;
	private String chavePublica;

	@JsonIgnore
	@OneToMany(mappedBy = "identificadoresId")
	private Collection<Rep> repCollection;

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized String getVersaoApl() {
		return versaoApl;
	}

	public synchronized void setVersaoApl(String versaoApl) {
		this.versaoApl = versaoApl;
	}

	public synchronized String getIdApr() {
		return idApr;
	}

	public synchronized void setIdApr(String idApr) {
		this.idApr = idApr;
	}

	public synchronized String getVersaoMrp() {
		return versaoMrp;
	}

	public synchronized void setVersaoMrp(String versaoMrp) {
		this.versaoMrp = versaoMrp;
	}

	public synchronized String getIdMrp() {
		return idMrp;
	}

	public synchronized void setIdMrp(String idMrp) {
		this.idMrp = idMrp;
	}

	public synchronized String getVersaoTamper() {
		return versaoTamper;
	}

	public synchronized void setVersaoTamper(String versaoTamper) {
		this.versaoTamper = versaoTamper;
	}

	public synchronized String getVersaoBoot() {
		return versaoBoot;
	}

	public synchronized void setVersaoBoot(String versaoBoot) {
		this.versaoBoot = versaoBoot;
	}

	public synchronized String getVersaoBio() {
		return versaoBio;
	}

	public synchronized void setVersaoBio(String versaoBio) {
		this.versaoBio = versaoBio;
	}

	public synchronized String getVersaoProx() {
		return versaoProx;
	}

	public synchronized void setVersaoProx(String versaoProx) {
		this.versaoProx = versaoProx;
	}

	public synchronized String getVersaoImp() {
		return versaoImp;
	}

	public synchronized void setVersaoImp(String versaoImp) {
		this.versaoImp = versaoImp;
	}

	public synchronized String getChavePublica() {
		return chavePublica;
	}

	public synchronized void setChavePublica(String chavePublica) {
		this.chavePublica = chavePublica;
	}

	@JsonIgnore
	public synchronized Collection<Rep> getRepCollection() {
		return repCollection;
	}

	public synchronized void setRepCollection(Collection<Rep> repCollection) {
		this.repCollection = repCollection;
	}

}
