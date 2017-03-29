package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.ColetaDumpCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ColetaDumping {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	private Integer enderecoInicio;

	private Integer enderecoFim;

	@JsonIgnore
	@OneToMany(mappedBy = "coletaDumpingId")
	private Collection<Tarefa> tarefaCollection;

	public synchronized Integer getEnderecoInicio() {
		return enderecoInicio;
	}

	public synchronized void setEnderecoInicio(Integer enderecoInicio) {
		this.enderecoInicio = enderecoInicio;
	}

	public synchronized Integer getEnderecoFim() {
		return enderecoFim;
	}

	public synchronized void setEnderecoFim(Integer enderecoFim) {
		this.enderecoFim = enderecoFim;
	}

	public ColetaDumpCmd toColetaDumpingCmd() {
		ColetaDumpCmd cmd = new ColetaDumpCmd();
		cmd.setcDEndFim(enderecoFim);
		cmd.setcDEndIni(enderecoInicio);
		return cmd;
	}
}
