package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.ConfiguracoesCartoesCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ConfiguracoesCartoes {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@JsonIgnore
	@OneToMany(mappedBy = "configuracoesCartoesId")
	private Collection<Tarefa> tarefaCollection;

	private String mascaraBarras;
	private String mascaraProx;
	private Integer digitosFixo;
	private String tipoBarras;
	private String tipoProx;

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

	public synchronized String getMascaraBarras() {
		return mascaraBarras;
	}

	public synchronized void setMascaraBarras(String mascaraBarras) {
		this.mascaraBarras = mascaraBarras;
	}

	public synchronized String getMascaraProx() {
		return mascaraProx;
	}

	public synchronized void setMascaraProx(String mascaraProx) {
		this.mascaraProx = mascaraProx;
	}

	public synchronized Integer getDigitosFixo() {
		return digitosFixo;
	}

	public synchronized void setDigitosFixo(Integer digitosFixo) {
		this.digitosFixo = digitosFixo;
	}

	public synchronized String getTipoBarras() {
		return tipoBarras;
	}

	public synchronized void setTipoBarras(String tipoBarras) {
		this.tipoBarras = tipoBarras;
	}

	public synchronized String getTipoProx() {
		return tipoProx;
	}

	public synchronized void setTipoProx(String tipoProx) {
		this.tipoProx = tipoProx;
	}

	public ConfiguracoesCartoesCmd toConfiguracoesCartoesCmd() {

		ConfiguracoesCartoesCmd cmd = new ConfiguracoesCartoesCmd();

		cmd.setCfgCDigFixo(this.digitosFixo.toString());
		cmd.setCfgCMascB(this.mascaraBarras);
		cmd.setCfgCMascP(this.mascaraProx);
		cmd.setCfgCTpB(this.tipoBarras);
		cmd.setCfgCTpP(this.tipoProx);

		return cmd;
	}

	/*
	 * CARTOES_MASCARA_BARRAS, CARTOES_MASCARA_PROX, CARTOES_DIGITOS_FIXO,
	 * CARTOES_TIPO_BARRAS, CARTOES_TIPO_PROX,
	 */

}
