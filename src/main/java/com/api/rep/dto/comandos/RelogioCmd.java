package com.api.rep.dto.comandos;

import com.api.rep.entity.Relogio;

public class RelogioCmd implements Cmd {

	private static final long serialVersionUID = 1L;

	private String cfgRelData;
	private String fgRelHora;

	public synchronized String getCfgRelData() {
		return cfgRelData;
	}

	public synchronized void setCfgRelData(String cfgRelData) {
		this.cfgRelData = cfgRelData;
	}

	public synchronized String getFgRelHora() {
		return fgRelHora;
	}

	public synchronized void setFgRelHora(String fgRelHora) {
		this.fgRelHora = fgRelHora;
	}

	public static synchronized long getSerialversionuid() {
		return serialVersionUID;
	}

	public Relogio toRelogio() {
		Relogio relogio = new Relogio();
		relogio.setData(this.cfgRelData);
		relogio.setHora(this.fgRelHora);

		return relogio;
	}
}
