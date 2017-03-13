package com.api.rep.dto.comandos;

import com.api.rep.entity.HorarioVerao;

public class HorarioVeraoCmd implements Cmd {

	private static final long serialVersionUID = 1L;

	private String cfgHVerIni;
	private String cfgHVerFim;

	public synchronized String getCfgHVerIni() {
		return cfgHVerIni;
	}

	public synchronized void setCfgHVerIni(String cfgHVerIni) {
		this.cfgHVerIni = cfgHVerIni;
	}

	public synchronized String getCfgHVerFim() {
		return cfgHVerFim;
	}

	public synchronized void setCfgHVerFim(String cfgHVerFim) {
		this.cfgHVerFim = cfgHVerFim;
	}

	public static synchronized long getSerialversionuid() {
		return serialVersionUID;
	}

	public HorarioVerao toHorarioVerao() {
		HorarioVerao horarioVerao = new HorarioVerao();
		horarioVerao.setDataFim(this.cfgHVerFim);
		horarioVerao.setDataInicio(this.cfgHVerIni);
		return horarioVerao;
	}

}
