package com.api.rep.dto.comunicacao;

import java.io.Serializable;

import com.api.rep.entity.Rep;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representa o objeto Rep que se autentica no sistema
 * 
 * @author juliano.ezequiel
 *
 */
public class RepDTO implements Serializable {

	private static final long serialVersionUID = 6063659709331831136L;

	private Integer id;
	private String numeroSerie;
	private String chaveComunicacao;

	public RepDTO() {
	}

	public RepDTO(Rep rep) {
		this.chaveComunicacao = rep.getChaveComunicacao();
		this.numeroSerie = rep.getNumeroSerie();
		this.id = rep.getId();
	}

	public RepDTO(String numeroSerie, String chave) {
		this.chaveComunicacao = chave;
		this.numeroSerie = numeroSerie;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public String getChaveComunicacao() {
		return chaveComunicacao;
	}

	public Integer getId() {
		return id;
	}

	@JsonIgnore
	public Rep getRep() {
		return new Rep(this.id, this.numeroSerie, this.chaveComunicacao);
	}

}
