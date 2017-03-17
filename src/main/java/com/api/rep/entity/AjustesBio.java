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
}
