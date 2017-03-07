package com.api.simulador.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RepSimulador {

	@Id
	@GeneratedValue
	private Integer id;

	private String ip;

}
