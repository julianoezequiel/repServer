package com.api.rep.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.HorarioVeraoCmd;

@Entity
public class HorarioVerao {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	private String DataInicio;
	private String DataFim;

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized String getDataInicio() {
		return DataInicio;
	}

	public synchronized void setDataInicio(String dataInicio) {
		DataInicio = dataInicio;
	}

	public synchronized String getDataFim() {
		return DataFim;
	}

	public synchronized void setDataFim(String dataFim) {
		DataFim = dataFim;
	}

	public HorarioVeraoCmd toHorarioVeraoCmd() {
		HorarioVeraoCmd cmd = new HorarioVeraoCmd();
		cmd.setCfgHVerFim(this.DataFim);
		cmd.setCfgHVerIni(this.DataInicio);

		return cmd;
	}

}
