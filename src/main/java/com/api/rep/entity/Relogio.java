package com.api.rep.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.api.rep.dto.comandos.RelogioCmd;

@Entity
public class Relogio {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	private String data;
	private String hora;

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized String getData() {
		return data;
	}

	public synchronized void setData(String data) {
		this.data = data;
	}

	public synchronized String getHora() {
		return hora;
	}

	public synchronized void setHora(String hora) {
		this.hora = hora;
	}

	public RelogioCmd toRelogioCmd() {
		RelogioCmd cmd = new RelogioCmd();
		cmd.setCfgRelData(this.data);
		cmd.setFgRelHora(this.hora);

		return cmd;
	}

}
