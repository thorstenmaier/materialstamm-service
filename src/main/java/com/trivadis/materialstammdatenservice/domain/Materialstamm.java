package com.trivadis.materialstammdatenservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Materialstamm {

	@Id
	@GeneratedValue
	private Long id;
	private String bezeichnung;

	public Materialstamm() {
		super();
	}

	public Materialstamm(String bezeichnung) {
		super();
		this.bezeichnung = bezeichnung;
	}

	public Materialstamm(Long id, String bezeichnung) {
		this.id = id;
		this.bezeichnung = bezeichnung;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
	public String toString() {
		return "Materialstamm [id=" + id + ", bezeichnung=" + bezeichnung + "]";
	}

	// Alt Shift S

}
