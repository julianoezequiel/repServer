/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.rep.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juliano.ezequiel
 */
@Entity
@Table(name = "nsr")
@XmlRootElement
public class Nsr implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Column(name = "numero_nsr")
	private Integer numeroNsr;

	@Size(max = 600)
	@Column(name = "registro")
	private String registro;

	@JoinColumn(name = "rep_id", referencedColumnName = "id")
	@ManyToOne
	private Rep repId;

	@Column(name = "tipo")
	private Integer tipo;

	@Column(name = "data_gravacao")
	@Temporal(TemporalType.DATE)
	private Date dataGravacao;

	@Column(name = "horario_gravacao")
	@Temporal(TemporalType.TIME)
	private Date horarioGravacao;

	@Column(name = "data_marcacao")
	@Temporal(TemporalType.DATE)
	private Date dataMarcacao;

	@Column(name = "horario_marcacao")
	@Temporal(TemporalType.TIME)
	private Date horarioMarcacao;

	@Column(name = "tipo_indentificador")
	private Integer tipoIndentificador;

	@Column(name = "cnpj_cpf")
	private String cnpj_cpf;

	@Column(name = "cei")
	private String cei;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "local")
	private String local;

	@Column(name = "crc")
	private String crc;

	@Column(name = "pis")
	private String pis;

	@Column(name = "data_antes_ajuste")
	@Temporal(TemporalType.DATE)
	private Date dataAntesAjuste;

	@Column(name = "hora_antes_ajuste")
	@Temporal(TemporalType.TIME)
	private Date horaAntesAjuste;

	@Column(name = "data_ajustada")
	@Temporal(TemporalType.DATE)
	private Date dataAjustada;

	@Column(name = "hora_ajustada")
	@Temporal(TemporalType.TIME)
	private Date horaAjustada;

	@Column(name = "cpf_responsavel")
	private String cpfResponsavel;

	@Column(name = "tipo_operacao")
	private String tipoOperacao;

	@Column(name = "nome_empregado")
	private String nomeEmpregado;

	@Column(name = "dados_empregado")
	private String dadosEmpregado;

	@Column(name = "tipo_evento")
	private String tipoEvento;

	public Nsr() {
	}

	public Nsr(Integer numeroNsr, String registro, Rep repId) {
		super();
		this.numeroNsr = numeroNsr;
		this.registro = registro;
		this.repId = repId;
	}

	public Nsr(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroNsr() {
		return numeroNsr;
	}

	public void setNumeroNsr(Integer numeroNsr) {
		this.numeroNsr = numeroNsr;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Rep getRepId() {
		return repId;
	}

	public void setRepId(Rep repId) {
		this.repId = repId;
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
		if (!(object instanceof Nsr)) {
			return false;
		}
		Nsr other = (Nsr) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.api.rep.entity.Nsr[ id=" + id + " ]";
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Date getDataGravacao() {
		return dataGravacao;
	}

	public void setDataGravacao(Date dataGravacao) {
		this.dataGravacao = dataGravacao;
	}

	public Date getHorarioGravacao() {
		return horarioGravacao;
	}

	public void setHorarioGravacao(Date horarioGravacao) {
		this.horarioGravacao = horarioGravacao;
	}

	public Integer getTipoIndentificador() {
		return tipoIndentificador;
	}

	public void setTipoIndentificador(Integer tipoIndentificador) {
		this.tipoIndentificador = tipoIndentificador;
	}

	public String getCnpj_cpf() {
		return cnpj_cpf;
	}

	public void setCnpj_cpf(String cnpj_cpf) {
		this.cnpj_cpf = cnpj_cpf;
	}

	public String getCei() {
		return cei;
	}

	public void setCei(String cei) {
		this.cei = cei;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public Date getDataAntesAjuste() {
		return dataAntesAjuste;
	}

	public void setDataAntesAjuste(Date dataAntesAjuste) {
		this.dataAntesAjuste = dataAntesAjuste;
	}

	public Date getHoraAntesAjuste() {
		return horaAntesAjuste;
	}

	public void setHoraAntesAjuste(Date horaAntesAjuste) {
		this.horaAntesAjuste = horaAntesAjuste;
	}

	public Date getDataAjustada() {
		return dataAjustada;
	}

	public void setDataAjustada(Date dataAjustada) {
		this.dataAjustada = dataAjustada;
	}

	public Date getHoraAjustada() {
		return horaAjustada;
	}

	public void setHoraAjustada(Date horaAjustada) {
		this.horaAjustada = horaAjustada;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getNomeEmpregado() {
		return nomeEmpregado;
	}

	public void setNomeEmpregado(String nomeEmpregado) {
		this.nomeEmpregado = nomeEmpregado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDataMarcacao() {
		return dataMarcacao;
	}

	public void setDataMarcacao(Date dataMarcacao) {
		this.dataMarcacao = dataMarcacao;
	}

	public Date getHorarioMarcacao() {
		return horarioMarcacao;
	}

	public void setHorarioMarcacao(Date horarioMarcacao) {
		this.horarioMarcacao = horarioMarcacao;
	}

	public String getDadosEmpregado() {
		return dadosEmpregado;
	}

	public void setDadosEmpregado(String dadosEmpregado) {
		this.dadosEmpregado = dadosEmpregado;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
}
