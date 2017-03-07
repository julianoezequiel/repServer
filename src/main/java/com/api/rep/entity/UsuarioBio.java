package com.api.rep.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class UsuarioBio {

	@Id
	@GeneratedValue
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	private Integer id;

	private String pis;

//	@Type(type = "org.hibernate.type.BinaryType")
//	@Lob
	private byte[] template;

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

	public byte[] getTemplate() {
		return template;
	}

	public void setTemplate(byte[] template) {
		this.template = template;
	}

}
