package br.ufpr.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Genero implements BusinessEnum {
	
	M("M", "Masculino"),
	F("F", "Feminino");
	
	private String key;
	private String value;
	
	Genero(String key, String value) {
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
