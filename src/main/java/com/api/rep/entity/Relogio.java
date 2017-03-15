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

import com.api.rep.dto.comandos.RelogioCmd;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Relogio {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@JsonIgnore
	@OneToMany(mappedBy = "relogioId")
	private Collection<Rep> repCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "relogioId")
	private Collection<Tarefa> tarefaCollection;

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized Date getData() {
		return data;
	}

	public synchronized void setData(Date data) {
		this.data = data;
	}

	public RelogioCmd toRelogioCmd() {
		RelogioCmd cmd = new RelogioCmd();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		cmd.setCfgRelAno(calendar.get(Calendar.YEAR));
		cmd.setCfgRelDia(calendar.get(Calendar.DATE));
		cmd.setCfgRelHora(calendar.get(Calendar.HOUR_OF_DAY));
		cmd.setCfgRelMes(calendar.get(Calendar.MONTH) + 1);
		cmd.setCfgRelMin(calendar.get(Calendar.MINUTE));
		cmd.setCfgRelSeg(calendar.get(Calendar.SECOND));

		return cmd;
	}

}
