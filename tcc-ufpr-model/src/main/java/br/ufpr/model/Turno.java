package br.ufpr.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Turno implements BusinessEnum {
	
	MANHA("M", "Manhã"),
	TARDE("T", "Tarde"),
	NOITE("N", "Noite");

	private String key;
	private String value;
	
	private Turno(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public String getValue() {
		return this.value;
	}

}
