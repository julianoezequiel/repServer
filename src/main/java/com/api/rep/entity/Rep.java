/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.rep.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author juliano.ezequiel
 */
@Entity
@Table(name = "rep")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Rep.findAll", query = "SELECT r FROM Rep r"),
		@NamedQuery(name = "Rep.findById", query = "SELECT r FROM Rep r WHERE r.id = :id"),
		@NamedQuery(name = "Rep.findByChaveComunicacao", query = "SELECT r FROM Rep r WHERE r.chaveComunicacao = :chaveComunicacao"),
		@NamedQuery(name = "Rep.findByNumeroSerie", query = "SELECT r FROM Rep r WHERE r.numeroSerie = :numeroSerie") })
public class Rep implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Size(max = 255)
	@Column(name = "chave_comunicacao")
	private String chaveComunicacao;

	@Size(max = 255)
	@Column(name = "numero_serie")
	private String numeroSerie;

	@JsonIgnore
	@OneToMany(mappedBy = "repId", cascade = CascadeType.ALL)
	private Collection<Nsr> nsrCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "repId", cascade = CascadeType.ALL)
	private Collection<Tarefa> tarefaCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "repId", cascade = CascadeType.ALL)
	private Collection<Empregado> empregadoCollection;

	@JoinColumn(name = "empregado_id", referencedColumnName = "id")
	@ManyToOne
	@JsonIgnore
	private Empregado empregadoId;

	@JoinColumn(name = "empregador_id", referencedColumnName = "id")
	@ManyToOne
	@JsonIgnore
	private Empregador empregadorId;

	@JoinColumn(name = "configuracoes_senha_id", referencedColumnName = "id")
	@ManyToOne
	private ConfiguracoesSenha configurcacoesSenhaId;

	@JoinColumn(name = "configuracoes_cartoes_id", referencedColumnName = "id")
	@ManyToOne
	private ConfiguracoesCartoes configuracoesCartoesId;

	@JoinColumn(name = "configuracoes_rede_id", referencedColumnName = "id")
	@ManyToOne
	private ConfiguracoesRede configuracoesRedeId;

	@JoinColumn(name = "relogio_id", referencedColumnName = "id")
	@ManyToOne
	private Relogio relogioId;

	@JoinColumn(name = "horario_verao_id", referencedColumnName = "id")
	@ManyToOne
	private HorarioVerao horarioVeraoId;

	@JoinColumn(name = "indentificadores_id", referencedColumnName = "id")
	@ManyToOne
	private Identificadores identificadoresId;

	@JoinColumn(name = "info_id", referencedColumnName = "id")
	@ManyToOne
	private Info infoId;

	private Integer ultimoNsr;

	public Rep() {
	}

	public Rep(Integer id) {
		this.id = id;
	}

	public Rep(Integer id2, String numeroSerie2, String chaveComunicacao2) {
		this.id = id2;
		this.numeroSerie = numeroSerie2;
		this.chaveComunicacao = chaveComunicacao2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChaveComunicacao() {
		return chaveComunicacao;
	}

	public void setChaveComunicacao(String chaveComunicacao) {
		this.chaveComunicacao = chaveComunicacao;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	@XmlTransient
	public Collection<Tarefa> getTarefaCollection() {
		return tarefaCollection;
	}

	public void setTarefaCollection(Collection<Tarefa> tarefaCollection) {
		this.tarefaCollection = tarefaCollection;
	}

	public synchronized ConfiguracoesSenha getConfigurcacoesSenhaId() {
		return configurcacoesSenhaId;
	}

	public synchronized void setConfigurcacoesSenhaId(ConfiguracoesSenha configurcacoesSenhaId) {
		this.configurcacoesSenhaId = configurcacoesSenhaId;
	}

	public static synchronized long getSerialversionuid() {
		return serialVersionUID;
	}

	public synchronized HorarioVerao getHorarioVeraoId() {
		return horarioVeraoId;
	}

	public synchronized void setHorarioVeraoId(HorarioVerao horarioVeraoId) {
		this.horarioVeraoId = horarioVeraoId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Rep)) {
			return false;
		}
		Rep other = (Rep) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.api.rep.entity.Rep[ id=" + id + " ]";
	}

	@XmlTransient
	public Collection<Nsr> getNsrCollection() {
		return nsrCollection;
	}

	public void setNsrCollection(Collection<Nsr> nsrCollection) {
		this.nsrCollection = nsrCollection;
	}

	public Empregado getEmpregadoId() {
		return empregadoId;
	}

	public void setEmpregadoId(Empregado empregadoId) {
		this.empregadoId = empregadoId;
	}

	public synchronized Empregador getEmpregadorId() {
		return empregadorId;
	}

	public synchronized void setEmpregadorId(Empregador empregadorId) {
		this.empregadorId = empregadorId;
	}

	public synchronized ConfiguracoesCartoes getConfiguracoesCartoesId() {
		return configuracoesCartoesId;
	}

	public synchronized void setConfiguracoesCartoesId(ConfiguracoesCartoes configuracoesCartoesId) {
		this.configuracoesCartoesId = configuracoesCartoesId;
	}

	public synchronized ConfiguracoesRede getConfiguracoesRedeId() {
		return configuracoesRedeId;
	}

	public synchronized void setConfiguracoesRedeId(ConfiguracoesRede configuracoesRedeId) {
		this.configuracoesRedeId = configuracoesRedeId;
	}

	public synchronized Integer getUltimoNsr() {
		return ultimoNsr;
	}

	public synchronized void setUltimoNsr(Integer ultimoNsr) {
		this.ultimoNsr = ultimoNsr;
	}

	public synchronized Relogio getRelogioId() {
		return relogioId;
	}

	public synchronized void setRelogioId(Relogio relogioId) {
		this.relogioId = relogioId;
	}

	public synchronized Identificadores getIdentificadoresId() {
		return identificadoresId;
	}

	public synchronized void setIdentificadoresId(Identificadores identificadoresId) {
		this.identificadoresId = identificadoresId;
	}

	public synchronized Info getInfoId() {
		return infoId;
	}

	public synchronized void setInfoId(Info infoId) {
		this.infoId = infoId;
	}

	public synchronized Collection<Empregado> getEmpregadoCollection() {
		return empregadoCollection;
	}

	public synchronized void setEmpregadoCollection(Collection<Empregado> empregadoCollection) {
		this.empregadoCollection = empregadoCollection;
	}
	
	

}
