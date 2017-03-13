package com.api.rep.dto.comandos;

import com.api.rep.entity.Empregado;

/**
 * Comando de Solicitacao e envio do Empregado. Os campos devem seguir as siglas
 * : fNome = Nome, fPis = Pis, fSenha = Senha, fNEx = Nome Exibição, fCB =
 * Número cartão Barrar, fCP = Número cartão Prox., fCT = Número Teclado, fPB =
 * Possui biometria
 * 
 * @author juliano.ezequiel
 *
 */
public class EmpregadoCmd implements Cmd {

	private static final long serialVersionUID = 1L;

	private String fNome;
	private String fPis;
	private String fSenha;
	private String fNEx;
	private String fCB;
	private String fCP;
	private String fCT;
	private Boolean fPB;

	public String getfNome() {
		return fNome;
	}

	public void setfNome(String empregadoNome) {
		this.fNome = empregadoNome;
	}

	public String getfPis() {
		return fPis;
	}

	public void setfPis(String empregadoPis) {
		this.fPis = empregadoPis;
	}

	public String getfNEx() {
		return fNEx;
	}

	public void setfNEx(String empregadoNomeExibe) {
		this.fNEx = empregadoNomeExibe;
	}

	public String getfCB() {
		return fCB;
	}

	public void setfCB(String empregadoCartaoBarras) {
		this.fCB = empregadoCartaoBarras;
	}

	public String getfCP() {
		return fCP;
	}

	public void setfCP(String empregadoCartaoProx) {
		this.fCP = empregadoCartaoProx;
	}

	public String getfCT() {
		return fCT;
	}

	public void setfCT(String empregadoCartaoTeclado) {
		this.fCT = empregadoCartaoTeclado;
	}

	public Boolean getfPB() {
		return fPB;
	}

	public void setfPB(Boolean empregadoPossuiBio) {
		this.fPB = empregadoPossuiBio;
	}

	public String getfSenha() {
		return fSenha;
	}

	public void setfSenha(String fSenha) {
		this.fSenha = fSenha;
	}

	public Empregado toEmpregado() {
		Empregado empregado = new Empregado();
		empregado.setEmpregadoCartaoBarras(fCB);
		empregado.setEmpregadoCartaoProx(fCP);
		empregado.setEmpregadoCartaoTeclado(fCT);
		empregado.setEmpregadoNome(fNome);
		empregado.setEmpregadoNomeExibe(fNEx);
		empregado.setEmpregadoPis(fPis);
		empregado.setEmpregadoPossuiBio(fPB);
		empregado.setEmpregadoSenha(fSenha);
		return empregado;
	}
}
