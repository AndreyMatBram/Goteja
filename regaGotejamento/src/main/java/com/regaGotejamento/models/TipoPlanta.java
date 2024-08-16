package com.regaGotejamento.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_TIPO_PLANTA")
public class TipoPlanta {
	private long id;
	private String tipo;

	public TipoPlanta() {

	}

	public TipoPlanta(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TIPO_PLANTA_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "TIPO_PLANTA", length = 100, nullable = false, unique = true)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}