package br.ufpr.services;

import java.io.Serializable;

import br.ufpr.domain.DomainObject;
import br.ufpr.exception.MissingIdException;

public interface CrudService<D extends DomainObject, ID extends Serializable> {
	
	D create(D domainObject);
	
	D update(D domainObject) throws MissingIdException;

	D find(ID id);
}
