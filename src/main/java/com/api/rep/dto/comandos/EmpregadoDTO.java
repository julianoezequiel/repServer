package com.api.rep.dto.comandos;

import com.api.rep.entity.Empregado;

public class EmpregadoDTO extends ComandoAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String empregadoNome;
	private String empregadoPis;
	private String empregadoNomeExibe;
	private String empregadoCartaoBarras;
	private String empregadoCartaoProx;
	private String empregadoCartaoTeclado;
	private Boolean empregadoPossuiBio;

	public String getEmpregadoNome() {
		return empregadoNome;
	}

	public void setEmpregadoNome(String empregadoNome) {
		this.empregadoNome = empregadoNome;
	}

	public String getEmpregadoPis() {
		return empregadoPis;
	}

	public void setEmpregadoPis(String empregadoPis) {
		this.empregadoPis = empregadoPis;
	}

	public String getEmpregadoNomeExibe() {
		return empregadoNomeExibe;
	}

	public void setEmpregadoNomeExibe(String empregadoNomeExibe) {
		this.empregadoNomeExibe = empregadoNomeExibe;
	}

	public String getEmpregadoCartaoBarras() {
		return empregadoCartaoBarras;
	}

	public void setEmpregadoCartaoBarras(String empregadoCartaoBarras) {
		this.empregadoCartaoBarras = empregadoCartaoBarras;
	}

	public String getEmpregadoCartaoProx() {
		return empregadoCartaoProx;
	}

	public void setEmpregadoCartaoProx(String empregadoCartaoProx) {
		this.empregadoCartaoProx = empregadoCartaoProx;
	}

	public String getEmpregadoCartaoTeclado() {
		return empregadoCartaoTeclado;
	}

	public void setEmpregadoCartaoTeclado(String empregadoCartaoTeclado) {
		this.empregadoCartaoTeclado = empregadoCartaoTeclado;
	}

	public Boolean getEmpregadoPossuiBio() {
		return empregadoPossuiBio;
	}

	public void setEmpregadoPossuiBio(Boolean empregadoPossuiBio) {
		this.empregadoPossuiBio = empregadoPossuiBio;
	}

	public Empregado toEmpregado() {
		Empregado empregado = new Empregado();
		empregado.setEmpregadoCartaoBarras(empregadoCartaoBarras);
		empregado.setEmpregadoCartaoProx(empregadoCartaoProx);
		empregado.setEmpregadoCartaoTeclado(empregadoCartaoTeclado);
		empregado.setEmpregadoNome(empregadoNome);
		empregado.setEmpregadoNomeExibe(empregadoNomeExibe);
		empregado.setEmpregadoPis(empregadoPis);
		empregado.setEmpregadoPossuiBio(empregadoPossuiBio);
		return empregado;
	}
}
