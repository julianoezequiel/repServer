package com.api.rep.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.Cmd;
import com.api.rep.dto.comandos.EmpregadorCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Empregador implements Serializable {

	private static final long serialVersionUID = 5790023392364826111L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	private String empregadorIdent;

	private String empregadorCei;

	private String empregadorLocal;

	private String empregadorRazao;

	private String empregadorTipoIdent;

	@JsonIgnore
	@OneToMany(mappedBy = "empregadorId")
	private Collection<Rep> repCollection = new ArrayList<>();

	public Empregador(String empregadorIdent) {
		super();
		this.empregadorIdent = empregadorIdent;
	}

	public Empregador() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpregadorIdent() {
		return empregadorIdent;
	}

	public void setEmpregadorIdent(String empregadorIdent) {
		this.empregadorIdent = empregadorIdent;
	}

	public String getEmpregadorCei() {
		return empregadorCei;
	}

	public void setEmpregadorCei(String empregadorCei) {
		this.empregadorCei = empregadorCei;
	}

	public String getEmpregadorLocal() {
		return empregadorLocal;
	}

	public void setEmpregadorLocal(String empregadorLocal) {
		this.empregadorLocal = empregadorLocal;
	}

	public String getEmpregadorRazao() {
		return empregadorRazao;
	}

	public void setEmpregadorRazao(String empregadorRazao) {
		this.empregadorRazao = empregadorRazao;
	}

	public String getEmpregadorTipoIdent() {
		return empregadorTipoIdent;
	}

	public void setEmpregadorTipoIdent(String empregadorTipoIdent) {
		this.empregadorTipoIdent = empregadorTipoIdent;
	}

	public Collection<Rep> getRepCollection() {
		return repCollection;
	}

	public void setRepCollection(Collection<Rep> repCollection) {
		this.repCollection = repCollection;
	}

	public Cmd toEmpregadorDTO() {
		EmpregadorCmd dto = new EmpregadorCmd();
		dto.seteCei(empregadorCei);
		dto.seteId(empregadorIdent);
		dto.seteLoc(empregadorLocal);
		dto.seteRS(empregadorRazao);
		dto.seteTpId(empregadorTipoIdent);
		return dto;
	}

	public static Empregador clear(Empregador empregador) {
		empregador.setRepCollection(null);
		return empregador;
	}

}
