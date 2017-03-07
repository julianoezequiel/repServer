package com.api.rep.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.ComandoAbstract;
import com.api.rep.dto.comandos.EmpregadoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Empregado implements Serializable {

	private static final long serialVersionUID = -5424615695707253104L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@JsonIgnore
	@OneToMany(mappedBy = "empregadoId")
	private Collection<Tarefa> tarefaCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "empregadoId")
	private Collection<Rep> repCollection;

	private String empregadoNome;
	private String empregadoPis;
	private String empregadoNomeExibe;
	private String empregadoCartaoBarras;
	private String empregadoCartaoProx;
	private String empregadoCartaoTeclado;
	private Boolean empregadoPossuiBio;

	
	
	public Empregado(String empregadoPis) {
		super();
		this.empregadoPis = empregadoPis;
	}

	public Empregado() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<Tarefa> getTarefaCollection() {
		return tarefaCollection;
	}

	public void setTarefaCollection(Collection<Tarefa> tarefaCollection) {
		this.tarefaCollection = tarefaCollection;
	}

	public Collection<Rep> getRepCollection() {
		return repCollection;
	}

	public void setRepCollection(Collection<Rep> repCollection) {
		this.repCollection = repCollection;
	}

	public String getEmpregadoNome() {
		return empregadoNome;
	}

	public void setEmpregadoNome(String empregadoNome) {
		this.empregadoNome = empregadoNome;
	}

	public String getEmpregadoPis() {
		return empregadoPis;
	}

	public void setEmpregadoPis(String empregadoPis) {
		this.empregadoPis = empregadoPis;
	}

	public String getEmpregadoNomeExibe() {
		return empregadoNomeExibe;
	}

	public void setEmpregadoNomeExibe(String empregadoNomeExibe) {
		this.empregadoNomeExibe = empregadoNomeExibe;
	}

	public String getEmpregadoCartaoBarras() {
		return empregadoCartaoBarras;
	}

	public void setEmpregadoCartaoBarras(String empregadoCartaoBarras) {
		this.empregadoCartaoBarras = empregadoCartaoBarras;
	}

	public String getEmpregadoCartaoProx() {
		return empregadoCartaoProx;
	}

	public void setEmpregadoCartaoProx(String empregadoCartaoProx) {
		this.empregadoCartaoProx = empregadoCartaoProx;
	}

	public String getEmpregadoCartaoTeclado() {
		return empregadoCartaoTeclado;
	}

	public void setEmpregadoCartaoTeclado(String empregadoCartaoTeclado) {
		this.empregadoCartaoTeclado = empregadoCartaoTeclado;
	}

	public Boolean getEmpregadoPossuiBio() {
		return empregadoPossuiBio;
	}

	public void setEmpregadoPossuiBio(Boolean empregadoPossuiBio) {
		this.empregadoPossuiBio = empregadoPossuiBio;
	}

	public ComandoAbstract toEmpregadoDTO() {
		EmpregadoDTO dto = new EmpregadoDTO();
		dto.setEmpregadoCartaoBarras(empregadoCartaoBarras);
		dto.setEmpregadoCartaoProx(empregadoCartaoProx);
		dto.setEmpregadoCartaoTeclado(empregadoCartaoTeclado);
		dto.setEmpregadoNome(empregadoNome);
		dto.setEmpregadoNomeExibe(empregadoNomeExibe);
		dto.setEmpregadoPis(empregadoPis);
		dto.setEmpregadoPossuiBio(empregadoPossuiBio);

		return dto;
	}

}
