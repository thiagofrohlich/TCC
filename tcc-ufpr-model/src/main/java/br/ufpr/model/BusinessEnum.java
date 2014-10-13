package br.ufpr.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public interface BusinessEnum {

	String getKey();
	String getValue();
	
}
