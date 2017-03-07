package com.api.rep.dto.comunicacao;

import java.io.Serializable;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comandos.ComandoAbstract;
import com.api.rep.entity.Tarefa;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Todas os comandos solicitados para o Rep relizar, são contruidos pela
 * {@link TarefaDTO}
 * 
 * @author juliano.ezequiel
 *
 */
@JsonPropertyOrder(value = { "nsu", "url", "cpf", "tipoComando", "tipoOperacao", "dadosComando" })
public class TarefaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6765797816369891472L;

	private Integer nsu;
	private String url;
	private Integer tipoOperacao;
	private Integer tipoComando;
	private String cpf;

	private ComandoAbstract dadosComando;

	public TarefaDTO() {
	}

	public TarefaDTO(CONSTANTES.TIPO_OPERACAO TIPO_OPERACAO, CONSTANTES.TIPO_CMD TIPO_CMD, Integer nsu) {
		super();
		this.tipoOperacao = TIPO_OPERACAO.ordinal();
		this.tipoComando = TIPO_CMD.ordinal();
		this.nsu = nsu;
	}

	public TarefaDTO(Tarefa tarefa) {
		super();
		this.tipoOperacao = CONSTANTES.TIPO_OPERACAO.get(tarefa.getTipoOperacao()).ordinal();
		this.nsu = tarefa.getNsu();
		this.url = CONSTANTES.TIPO_CMD.get(tarefa.getTipoTarefa()).getUrl();
	}

	public Integer getNsu() {
		return nsu;
	}

	public void setNsu(Integer nsu) {
		this.nsu = nsu;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public ComandoAbstract getDadosComando() {
		return dadosComando;
	}

	public void setDadosComando(ComandoAbstract dadosComando) {
		this.dadosComando = dadosComando;
	}

	public Integer getTipoComando() {
		return tipoComando;
	}

	public void setTipoComando(Integer tipoComando) {
		this.tipoComando = tipoComando;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Tarefa toTarefa(){
		Tarefa tarefa = new Tarefa();
		this.dadosComando.getClass().getSuperclass();
		
		return tarefa;
	}

}
