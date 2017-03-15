package com.api.rep.entity;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.HorarioVeraoCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class HorarioVerao {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date DataInicio;

	@Temporal(TemporalType.DATE)
	private Date DataFim;

	@JsonIgnore
	@OneToMany(mappedBy = "horarioVeraoId")
	private Collection<Tarefa> tarefaCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "horarioVeraoId")
	private Collection<Rep> repCollection;

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized Date getDataInicio() {
		return DataInicio;
	}

	public synchronized void setDataInicio(Date dataInicio) {
		DataInicio = dataInicio;
	}

	public synchronized Date getDataFim() {
		return DataFim;
	}

	public synchronized void setDataFim(Date dataFim) {
		DataFim = dataFim;
	}

	public HorarioVeraoCmd toHorarioVeraoCmd() {

		HorarioVeraoCmd cmd = new HorarioVeraoCmd();

		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(this.DataInicio);

		Calendar calendarFim = Calendar.getInstance();
		calendarFim.setTime(this.DataFim);

		cmd.setCfgHVerAnoF(calendarFim.get(Calendar.YEAR));
		cmd.setCfgHVerAnoI(calendarInicio.get(Calendar.YEAR));
		cmd.setCfgHVerDiaF(calendarFim.get(Calendar.DAY_OF_MONTH));
		cmd.setCfgHVerDiaI(calendarInicio.get(Calendar.DAY_OF_MONTH));
		cmd.setCfgHVerMesF(calendarFim.get(Calendar.MONTH) + 1);
		cmd.setCfgHVerMesI(calendarInicio.get(Calendar.MONTH) + 1);

		return cmd;
	}

}
