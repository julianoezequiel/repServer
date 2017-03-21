package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.AjustesBioCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AjustesBio {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@JsonIgnore
	@OneToMany(mappedBy = "ajustesBioId")
	private Collection<Tarefa> tarefaCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "ajustesBioId")
	private Collection<Rep> repCollection;

	private Integer segurancaIdentificacao;
	private Integer segurancaVerificacao;
	private Integer segurancaFiltroLatente;
	private Integer capturaAdaptiva;
	private Integer nivelLFD;
	private Integer timeout;
	private Integer dedoDuplicado;

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

	public synchronized Integer getSegurancaIdentificacao() {
		return segurancaIdentificacao;
	}

	public synchronized void setSegurancaIdentificacao(Integer segurancaIdentificacao) {
		this.segurancaIdentificacao = segurancaIdentificacao;
	}

	public synchronized Integer getSegurancaVerificacao() {
		return segurancaVerificacao;
	}

	public synchronized void setSegurancaVerificacao(Integer segurancaVerificacao) {
		this.segurancaVerificacao = segurancaVerificacao;
	}

	public synchronized Integer getSegurancaFiltroLatente() {
		return segurancaFiltroLatente;
	}

	public synchronized void setSegurancaFiltroLatente(Integer segurancaFiltroLatente) {
		this.segurancaFiltroLatente = segurancaFiltroLatente;
	}

	public synchronized Integer getCapturaAdaptiva() {
		return capturaAdaptiva;
	}

	public synchronized void setCapturaAdaptiva(Integer capturaAdaptiva) {
		this.capturaAdaptiva = capturaAdaptiva;
	}

	public synchronized Integer getNivelLFD() {
		return nivelLFD;
	}

	public synchronized void setNivelLFD(Integer nivelLFD) {
		this.nivelLFD = nivelLFD;
	}

	public synchronized Integer getTimeout() {
		return timeout;
	}

	public synchronized void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public synchronized Integer getDedoDuplicado() {
		return dedoDuplicado;
	}

	public synchronized void setDedoDuplicado(Integer dedoDuplicado) {
		this.dedoDuplicado = dedoDuplicado;
	}

	public AjustesBioCmd toAjustesBioCmd() {
		AjustesBioCmd cmd = new AjustesBioCmd();
		cmd.setCfgBCA(capturaAdaptiva);
		cmd.setCfgBDD(dedoDuplicado);
		cmd.setCfgBFL(segurancaFiltroLatente);
		cmd.setCfgBNLFD(nivelLFD);
		cmd.setCfgBSegI(segurancaIdentificacao);
		cmd.setCfgBSegV(segurancaVerificacao);
		cmd.setCfgBTO(timeout);

		return cmd;

	}
	// BIO_SEG_IDENT,
	// BIO_SEG_VER,
	// BIO_FILTRO_LATENTE,
	// BIO_CAPTURA_ADAPT,
	// BIO_NIVEL_LFD,
	// BIO_TIMEOUT,
	// BIO_DEDO_DUPLICADO,

}
