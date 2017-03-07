package com.api.rep.dto.comunicacao;

/**
 * Sempre que o Rep enviar um POST de status, deverá conter este objeto
 * 
 * @author juliano.ezequiel
 *
 */
public class StatusDTO {

	private Integer ultimoNsr;
	private Boolean config;

	public Boolean getConfig() {
		return config;
	}

	public Integer getUltimoNsr() {
		return ultimoNsr;
	}

	public void setConfig(Boolean config) {
		this.config = config;
	}

	public void setUltimoNsr(Integer ultimoNsr) {
		this.ultimoNsr = ultimoNsr;
	}

}
