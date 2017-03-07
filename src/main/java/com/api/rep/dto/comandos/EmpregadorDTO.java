package com.api.rep.dto.comandos;

import com.api.rep.entity.Empregador;

public class EmpregadorDTO extends ComandoAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String empregadorIdent;

	private String empregadorCei;

	private String empregadorLocal;

	private String empregadorRazao;

	private String empregadorTipoIdent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpregadorIdent() {
		return empregadorIdent;
	}

	public void setEmpregadorIdent(String empregadorIdent) {
		this.empregadorIdent = empregadorIdent;
	}

	public String getEmpregadorCei() {
		return empregadorCei;
	}

	public void setEmpregadorCei(String empregadorCei) {
		this.empregadorCei = empregadorCei;
	}

	public String getEmpregadorLocal() {
		return empregadorLocal;
	}

	public void setEmpregadorLocal(String empregadorLocal) {
		this.empregadorLocal = empregadorLocal;
	}

	public String getEmpregadorRazao() {
		return empregadorRazao;
	}

	public void setEmpregadorRazao(String empregadorRazao) {
		this.empregadorRazao = empregadorRazao;
	}

	public String getEmpregadorTipoIdent() {
		return empregadorTipoIdent;
	}

	public void setEmpregadorTipoIdent(String empregadorTipoIdent) {
		this.empregadorTipoIdent = empregadorTipoIdent;
	}

	public Empregador toEmpregador() {
		Empregador empregador = new Empregador();
		empregador.setEmpregadorCei(empregadorCei);
		empregador.setId(id);
		empregador.setEmpregadorIdent(empregadorIdent);
		empregador.setEmpregadorLocal(empregadorLocal);
		empregador.setEmpregadorRazao(empregadorRazao);
		empregador.setEmpregadorTipoIdent(empregadorTipoIdent);

		return empregador;

	}

}
