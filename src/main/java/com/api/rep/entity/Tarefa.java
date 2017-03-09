/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.rep.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comandos.ComandoAbstract;
import com.api.rep.dto.comunicacao.TarefaDTO;
import com.api.rep.service.tarefa.TarefaHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author juliano.ezequiel
 */
@Entity
@Table(name = "tarefa")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tarefa.findAll", query = "SELECT p FROM Tarefa p"),
		@NamedQuery(name = "Tarefa.findById", query = "SELECT p FROM Tarefa p WHERE p.id = :id"),
		@NamedQuery(name = "Tarefa.findByTipoOperacao", query = "SELECT p FROM Tarefa p WHERE p.tipoOperacao = :tipoOperacao"),
		@NamedQuery(name = "Tarefa.findByTipoTarefa", query = "SELECT p FROM Tarefa p WHERE p.tipoTarefa = :tipoTarefa"),
		@NamedQuery(name = "Tarefa.findByNsu", query = "SELECT p FROM Tarefa p WHERE p.nsu = :nsu") })
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer nsu;

	@Column(name = "tipo_operacao")
	private Integer tipoOperacao;

	@Column(name = "tipo_tarefa")
	private Integer tipoTarefa;

	/*
	 * @GeneratedValue
	 * 
	 * @Column(name = "nsu") private Integer nsu;
	 */

	@Column(name = "cpf")
	private String cpf;

	@JsonIgnore
	@JoinColumn(name = "rep_id", referencedColumnName = "id")
	@ManyToOne
	private Rep repId;

	@JoinColumn(name = "coleta_id", referencedColumnName = "id")
	@ManyToOne
	private Coleta coletaId;

	@JoinColumn(name = "empregado_id", referencedColumnName = "id")
	@ManyToOne
	private Empregado empregadoId;

	@JoinColumn(name = "empregador_id", referencedColumnName = "id")
	@ManyToOne
	private Empregador empregadorId;

	@JoinColumn(name = "configuracao_id", referencedColumnName = "id")
	@ManyToOne
	private Configuracao configuracaoId;

	public Tarefa() {
	}

	public Tarefa(Integer tipoOperacao, Integer tipoTarefa, Rep rep) {
		super();
		this.tipoOperacao = tipoOperacao;
		this.tipoTarefa = tipoTarefa;
		this.repId = rep;
	}
	/*
	 * public Integer getId() { return id; }
	 * 
	 * public void setId(Integer id) { this.id = id; }
	 */

	public Integer getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Integer getTipoTarefa() {
		return tipoTarefa;
	}

	public void setTipoTarefa(Integer tipoTarefa) {
		this.tipoTarefa = tipoTarefa;
	}

	public Integer getNsu() {
		return nsu;
	}

	public void setNsu(Integer nsu) {
		this.nsu = nsu;
	}

	public Rep getRepId() {
		return repId;
	}

	public void setRepId(Rep repId) {
		this.repId = repId;
	}

	public Coleta getColetaId() {
		return coletaId;
	}

	public void setColetaId(Coleta coletaId) {
		this.coletaId = coletaId;
	}

	public Empregado getEmpregadoId() {
		return empregadoId;
	}

	public void setEmpregadoId(Empregado empregadoId) {
		this.empregadoId = empregadoId;
	}

	public Configuracao getConfiguracaoId() {
		return configuracaoId;
	}

	public void setConfiguracaoId(Configuracao configuracaoId) {
		this.configuracaoId = configuracaoId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Empregador getEmpregadorId() {
		return empregadorId;
	}

	public void setEmpregadorId(Empregador empregadorId) {
		this.empregadorId = empregadorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nsu == null) ? 0 : nsu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (nsu == null) {
			if (other.nsu != null)
				return false;
		} else if (!nsu.equals(other.nsu))
			return false;
		return true;
	}

	/**
	 * Converte a entidade para objeto DTO
	 * 
	 * @return TarefaDTO
	 */
	public TarefaDTO toTarefaDTO() {
		TarefaDTO dto = new TarefaDTO();
		if (this.tipoOperacao != null) {
			dto.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.get(this.tipoOperacao).ordinal());
		}
		if (this.tipoTarefa != null) {
			dto.setUrl(TarefaHandler.TIPO_CMD.get(this.tipoTarefa).getUrl());
			dto.setTipoComando(TarefaHandler.TIPO_CMD.get(this.tipoTarefa).ordinal());
		}
		dto.setCpf(cpf);
		dto.setDadosComando(this.toComandoDTO());
		dto.setNsu(this.nsu);
		return dto;
	}

	/**
	 * Converte a Tarefa para o tipo de dado especifico
	 * 
	 * @return DadosComando
	 */
	public ComandoAbstract toComandoDTO() {

		if (this.coletaId != null) {
			return this.coletaId.toColetaDTO();
		} else if (this.configuracaoId != null) {
			return this.configuracaoId.toConfiguracaoDTO();
		} else if (this.empregadoId != null) {
			return this.empregadoId.toEmpregadoDTO();
		} else if (this.empregadorId != null) {
			return this.empregadorId.toEmpregadorDTO();
		} else {
			return null;
		}
	}

	public static Tarefa clear(Tarefa tarefa) {
		tarefa.setColetaId(null);
		tarefa.setConfiguracaoId(null);
		tarefa.setEmpregadoId(null);
		tarefa.setEmpregadorId(null);
		tarefa.setRepId(null);
		return tarefa;

	}

	public static Tarefa padraoTeste() {
		
		Tarefa tarefa = new Tarefa();
		tarefa.setCpf(CONSTANTES.CPF_TESTE);
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());

		return tarefa;
	}

}
