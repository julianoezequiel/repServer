package com.api.rep.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.ConfiguracaoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Configuracao{

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "configuracaoId")
	private Collection<Tarefa> tarefaCollection;
	
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
	
	public ConfiguracaoDTO toConfiguracaoDTO(){
		ConfiguracaoDTO configuracaoDTO = new ConfiguracaoDTO();
//		configuracaoDTO.setTIPO_CMD(CONSTANTES.TIPO_CMD.CONFIGURACAO);
		return configuracaoDTO;
	}
}
