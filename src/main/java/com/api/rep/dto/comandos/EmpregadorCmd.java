package com.api.rep.dto.comandos;

import com.api.rep.entity.Empregador;

/**
 * Comando de solicitacao e envio de Empregado(Funcionario) para Inner Rep. Os
 * campos devem seguir as Siglas: eRS = Razão Social, eTpId = Tipo
 * Identificador, eId = Identificador(CPF/CNPJ), eLoc = Local eCei = CEI
 * 
 * @author juliano.ezequiel
 *
 */
public class EmpregadorCmd implements Cmd {

	private static final long serialVersionUID = 1L;

	private String eId;

	private String eCei;

	private String eLoc;

	private String eRS;

	private String eTpId;

	public String geteId() {
		return eId;
	}

	public void seteId(String empregadorIdent) {
		this.eId = empregadorIdent;
	}

	public String geteCei() {
		return eCei;
	}

	public void seteCei(String empregadorCei) {
		this.eCei = empregadorCei;
	}

	public String geteLoc() {
		return eLoc;
	}

	public void seteLoc(String empregadorLocal) {
		this.eLoc = empregadorLocal;
	}

	public String geteRS() {
		return eRS;
	}

	public void seteRS(String empregadorRazao) {
		this.eRS = empregadorRazao;
	}

	public String geteTpId() {
		return eTpId;
	}

	public void seteTpId(String empregadorTipoIdent) {
		this.eTpId = empregadorTipoIdent;
	}

	public Empregador toEmpregador() {
		Empregador empregador = new Empregador();
		empregador.setEmpregadorCei(eCei);
		empregador.setEmpregadorIdent(eId);
		empregador.setEmpregadorLocal(eLoc);
		empregador.setEmpregadorRazao(eRS);
		empregador.setEmpregadorTipoIdent(eTpId);

		return empregador;

	}

}
