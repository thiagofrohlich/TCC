package br.ufpr.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Acesso implements BusinessEnum {
	
	CADASTRO("CAD", "Usuário com permissão de cadastro");
	
	private String key;
	private String value;
	
	Acesso(String key, String value) {
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
