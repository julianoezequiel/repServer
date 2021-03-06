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
import com.api.rep.dto.comandos.Cmd;
import com.api.rep.dto.comunicacao.ComandoDeEnvio;
import com.api.rep.service.comandos.CmdHandler;
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

	private Integer[] status;

	private Integer tentativas = 0;
	private Integer tipoUrl;

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

	@JoinColumn(name = "configuracoes_rede_id", referencedColumnName = "id")
	@ManyToOne
	private ConfiguracoesRede configuracoesRedeId;

	@JoinColumn(name = "configuracoes_senha_id", referencedColumnName = "id")
	@ManyToOne
	private ConfiguracoesSenha configuracoesSenhaId;

	@JoinColumn(name = "configuracoes_cartoes_id", referencedColumnName = "id")
	@ManyToOne
	private ConfiguracoesCartoes configuracoesCartoesId;

	@JoinColumn(name = "relogio_id", referencedColumnName = "id")
	@ManyToOne
	private Relogio relogioId;

	@JoinColumn(name = "horario_verao_id", referencedColumnName = "id")
	@ManyToOne
	private HorarioVerao horarioVeraoId;

	@JoinColumn(name = "ajustes_bio_id", referencedColumnName = "id")
	@ManyToOne
	private AjustesBio ajustesBioId;

	@JoinColumn(name = "configuracoes_webServer_id", referencedColumnName = "id")
	@ManyToOne
	private ConfiguracoesWebServer configuracoesWebServerId;

	@JoinColumn(name = "coleta_dumping_id", referencedColumnName = "id")
	@ManyToOne
	private ColetaDumping coletaDumpingId;

	public synchronized Integer getTipoUrl() {
		return tipoUrl;
	}

	public synchronized void setTipoUrl(Integer tipoUrl) {
		this.tipoUrl = tipoUrl;
	}

	public Tarefa() {
	}

	public Tarefa(Integer tipoOperacao, Integer tipoTarefa, Rep rep) {
		super();
		this.tipoOperacao = tipoOperacao;
		this.tipoTarefa = tipoTarefa;
		this.repId = rep;
	}

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

	public ConfiguracoesRede getConfiguracoesRedeId() {
		return configuracoesRedeId;
	}

	public void setConfiguracoesRedeId(ConfiguracoesRede configuracaoId) {
		this.configuracoesRedeId = configuracaoId;
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

	public ConfiguracoesSenha getConfiguracoesSenhaId() {
		return configuracoesSenhaId;
	}

	public void setConfiguracoesSenhaId(ConfiguracoesSenha configurcacoesSenha) {
		this.configuracoesSenhaId = configurcacoesSenha;
	}

	public ConfiguracoesCartoes getConfiguracoesCartoesId() {
		return configuracoesCartoesId;
	}

	public void setConfiguracoesCartoesId(ConfiguracoesCartoes configuracoesCartoesId) {
		this.configuracoesCartoesId = configuracoesCartoesId;
	}

	public static synchronized long getSerialversionuid() {
		return serialVersionUID;
	}

	public synchronized Relogio getRelogioId() {
		return relogioId;
	}

	public synchronized void setRelogioId(Relogio relogioId) {
		this.relogioId = relogioId;
	}

	public synchronized HorarioVerao getHorarioVeraoId() {
		return horarioVeraoId;
	}

	public synchronized void setHorarioVeraoId(HorarioVerao horarioVeraoId) {
		this.horarioVeraoId = horarioVeraoId;
	}

	public synchronized AjustesBio getAjustesBioId() {
		return ajustesBioId;
	}

	public synchronized void setAjustesBioId(AjustesBio ajustesBioId) {
		this.ajustesBioId = ajustesBioId;
	}

	public synchronized ConfiguracoesWebServer getConfiguracoesWebServerId() {
		return configuracoesWebServerId;
	}

	public synchronized void setConfiguracoesWebServerId(ConfiguracoesWebServer configurcacoesWebServerId) {
		this.configuracoesWebServerId = configurcacoesWebServerId;
	}

	public synchronized Integer getTentativas() {
		return tentativas;
	}

	public synchronized void setTentativas(Integer tentativas) {
		this.tentativas = tentativas;
	}

	public synchronized Integer[] getStatus() {
		return status;
	}

	public synchronized void setStatus(Integer[] status) {
		this.status = status;
	}

	public synchronized ColetaDumping getColetaDumpingId() {
		return coletaDumpingId;
	}

	public synchronized void setColetaDumpingId(ColetaDumping coletaDumpingId) {
		this.coletaDumpingId = coletaDumpingId;
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
	 * @return ComandoDeEnvio
	 */
	public ComandoDeEnvio toComandoDeEnvio() {
		ComandoDeEnvio dto = new ComandoDeEnvio();
		if (this.tipoOperacao != null) {
			dto.settOp(CONSTANTES.TIPO_OPERACAO.get(this.tipoOperacao).ordinal());
		}
		if (this.tipoTarefa != null) {
			dto.setUrl(CmdHandler.TIPO_CMD.get(this.tipoTarefa).getUrl(tipoUrl == null ? 0 : tipoUrl));
			dto.settCmd(CmdHandler.TIPO_CMD.get(this.tipoTarefa).ordinal());
		}
		dto.setCpf(cpf);
		dto.setdCmd(this.toComandoDTO());
		dto.setNsu(this.nsu);
		return dto;
	}

	/**
	 * Converte a Tarefa para o tipo de dado específico
	 * 
	 * @return DadosComando
	 */
	public Cmd toComandoDTO() {

		if (this.coletaId != null) {
			return this.coletaId.toColetaCmd();
		} else if (this.configuracoesRedeId != null) {
			return this.configuracoesRedeId.toConfiguracoesRedeCmd();
		} else if (this.empregadoId != null) {
			return this.empregadoId.toEmpregadoDTO();
		} else if (this.empregadorId != null) {
			return this.empregadorId.toEmpregadorDTO();
		} else if (this.configuracoesCartoesId != null) {
			return this.configuracoesCartoesId.toConfiguracoesCartoesCmd();
		} else if (this.configuracoesSenhaId != null) {
			return this.configuracoesSenhaId.toConfiguracaoCmd();
		} else if (this.relogioId != null) {
			return this.relogioId.toRelogioCmd();
		} else if (this.horarioVeraoId != null) {
			return this.horarioVeraoId.toHorarioVeraoCmd();
		} else if (this.ajustesBioId != null) {
			return this.ajustesBioId.toAjustesBioCmd();
		} else if (this.configuracoesWebServerId != null) {
			return this.configuracoesWebServerId.toConfigurcacoesWebServer();
		} else if (this.coletaDumpingId != null) {
			return this.coletaDumpingId.toColetaDumpingCmd();
		} else {
			return null;
		}
	}

	public static Tarefa clear(Tarefa tarefa) {
		tarefa.setColetaId(null);
		tarefa.setConfiguracoesRedeId(null);
		tarefa.setEmpregadoId(null);
		tarefa.setEmpregadorId(null);
		tarefa.setRepId(null);
		tarefa.setConfiguracoesCartoesId(null);
		tarefa.setConfiguracoesSenhaId(null);
		tarefa.setRelogioId(null);
		tarefa.setHorarioVeraoId(null);
		tarefa.setAjustesBioId(null);
		tarefa.setConfiguracoesWebServerId(null);
		tarefa.setColetaDumpingId(null);
		return tarefa;

	}

}
