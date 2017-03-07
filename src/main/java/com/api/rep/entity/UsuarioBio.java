package com.api.rep.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UsuarioBio {

	@Id
	@GeneratedValue
	private Integer id;

	private String pis;

	private Byte[] template;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public Byte[] getTemplate() {
		return template;
	}

	public void setTemplate(Byte[] template) {
		this.template = template;
	}

}
