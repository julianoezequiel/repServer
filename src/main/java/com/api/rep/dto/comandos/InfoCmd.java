package com.api.rep.dto.comandos;

import com.api.rep.entity.Info;

public class InfoCmd implements Cmd {

	private static final long serialVersionUID = 1L;

	private String infNProd;
	private String infMProd;
	private String infNumRep;
	private String infStatRep;
	private String infVerApl;
	private String infVerMrp;
	private String infNumFunc;
	private String infNumBio;
	private String infNsr;
	private String infTpBio;
	private String infMBio;
	private String infCpBio;
	private String infStatImp;

	public String getInfNProd() {
		return infNProd;
	}

	public void setInfNProd(String infNProd) {
		this.infNProd = infNProd;
	}

	public String getInfMProd() {
		return infMProd;
	}

	public void setInfMProd(String infMProd) {
		this.infMProd = infMProd;
	}

	public String getInfNumRep() {
		return infNumRep;
	}

	public void setInfNumRep(String infNumRep) {
		this.infNumRep = infNumRep;
	}

	public String getInfVerApl() {
		return infVerApl;
	}

	public void setInfVerApl(String infVerApl) {
		this.infVerApl = infVerApl;
	}

	public String getInfVerMrp() {
		return infVerMrp;
	}

	public void setInfVerMrp(String infVerMrp) {
		this.infVerMrp = infVerMrp;
	}

	public String getInfNumFunc() {
		return infNumFunc;
	}

	public void setInfNumFunc(String infNumFunc) {
		this.infNumFunc = infNumFunc;
	}

	public String getInfNumBio() {
		return infNumBio;
	}

	public void setInfNumBio(String infNumBio) {
		this.infNumBio = infNumBio;
	}

	public String getInfNsr() {
		return infNsr;
	}

	public void setInfNsr(String infNsr) {
		this.infNsr = infNsr;
	}

	public String getInfTpBio() {
		return infTpBio;
	}

	public void setInfTpBio(String infTpBio) {
		this.infTpBio = infTpBio;
	}

	public String getInfMBio() {
		return infMBio;
	}

	public void setInfMBio(String infMBio) {
		this.infMBio = infMBio;
	}

	public String getInfCpBio() {
		return infCpBio;
	}

	public void setInfCpBio(String infCpBio) {
		this.infCpBio = infCpBio;
	}

	public String getInfStatImp() {
		return infStatImp;
	}

	public void setInfStatImp(String infStatImp) {
		this.infStatImp = infStatImp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getInfStatRep() {
		return infStatRep;
	}

	public void setInfStatRep(String infStatRep) {
		this.infStatRep = infStatRep;
	}

	public Info toInfo() {

		Info info = new Info();

		info.setCapacidadeBio(new Integer(this.infCpBio));
		info.setModeloBio(this.infMBio);
		info.setModeloProduto(this.infMProd);
		info.setNomeProduto(this.infNProd);
		info.setNumeroRep(this.infNumRep);
		info.setNumeroUsuario(new Integer(this.infNumFunc));
		info.setNumeroUsuarioBio(new Integer(this.infNumBio));
		info.setStatusBloqueio(this.infStatRep);
		info.setStatusImpressora(this.infStatImp);
		info.setTipoBio(this.infTpBio);
		info.setUltimoNsr(this.infNsr);
		info.setVersaoApl(this.infVerApl);
		info.setVersaoMrp(this.infVerMrp);

		return info;
	}

}
