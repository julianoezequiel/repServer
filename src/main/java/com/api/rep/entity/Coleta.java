package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.ColetaCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Coleta {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	private Integer coletaNsrInicio;
	private Integer coletaNsrFim;

	private String coletaDataInicio;

	private String coletaDataFim;

	@JsonIgnore
	@OneToMany(mappedBy = "coletaId")
	private Collection<Tarefa> tarefaCollection;

	public Coleta() {
		// TODO Auto-generated constructor stub
	}

	public Coleta(Integer nsrInicio, Integer nsrFim) {
		super();
		this.coletaNsrInicio = nsrInicio;
		this.coletaNsrFim = nsrFim;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public synchronized Integer getColetaNsrInicio() {
		return coletaNsrInicio;
	}

	public synchronized void setColetaNsrInicio(Integer coletaNsrInicio) {
		this.coletaNsrInicio = coletaNsrInicio;
	}

	public synchronized Integer getColetaNsrFim() {
		return coletaNsrFim;
	}

	public synchronized void setColetaNsrFim(Integer coletaNsrFim) {
		this.coletaNsrFim = coletaNsrFim;
	}

	public synchronized String getColetaDataInicio() {
		return coletaDataInicio;
	}

	public synchronized void setColetaDataInicio(String coletaDataInicio) {
		this.coletaDataInicio = coletaDataInicio;
	}

	public synchronized String getColetaDataFim() {
		return coletaDataFim;
	}

	public synchronized void setColetaDataFim(String coletaDataFim) {
		this.coletaDataFim = coletaDataFim;
	}

	public Collection<Tarefa> getTarefaCollection() {
		return tarefaCollection;
	}

	public void setTarefaCollection(Collection<Tarefa> tarefaCollection) {
		this.tarefaCollection = tarefaCollection;
	}

	public ColetaCmd toColetaCmd() {
		ColetaCmd dto = new ColetaCmd();
		dto.setcNsrF(this.coletaNsrFim);
		dto.setcNsrI(this.coletaNsrInicio);
		dto.setcDtF(coletaDataFim);
		dto.setcDtI(coletaDataInicio);
		// dto.setTIPO_CMD(CONSTANTES.TIPO_CMD.NSR);
		return dto;

	}
}
