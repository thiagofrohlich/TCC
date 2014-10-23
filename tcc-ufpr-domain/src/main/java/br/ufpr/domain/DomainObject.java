package br.ufpr.domain;

import java.io.Serializable;

public interface DomainObject {

	Serializable getId();
	void delete();
	boolean isDeleted();
	
}
