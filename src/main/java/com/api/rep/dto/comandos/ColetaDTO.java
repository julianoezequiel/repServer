package com.api.rep.dto.comandos;

public class ColetaDTO extends ComandoAbstract {

	private static final long serialVersionUID = 1L;
	private Integer coletaNsrInicio;
	private Integer coletaNsrFim;

	private String coletaDataInicio;

	private String coletaDataFim;

	public synchronized Integer getColetaNsrInicio() {
		return coletaNsrInicio;
	}

	public synchronized void setColetaNsrInicio(Integer coletaNsrInicio) {
		this.coletaNsrInicio = coletaNsrInicio;
	}

	public synchronized Integer getColetaNsrFim() {
		return coletaNsrFim;
	}

	public synchronized void setColetaNsrFim(Integer coletaNsrFim) {
		this.coletaNsrFim = coletaNsrFim;
	}

	public synchronized String getColetaDataInicio() {
		return coletaDataInicio;
	}

	public synchronized void setColetaDataInicio(String coletaDataInicio) {
		this.coletaDataInicio = coletaDataInicio;
	}

	public synchronized String getColetaDataFim() {
		return coletaDataFim;
	}

	public synchronized void setColetaDataFim(String coletaDataFim) {
		this.coletaDataFim = coletaDataFim;
	}

}
