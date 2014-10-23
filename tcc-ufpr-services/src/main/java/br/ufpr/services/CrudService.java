package br.ufpr.services;

import java.io.Serializable;

import br.ufpr.domain.DomainObject;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;

public interface CrudService<D extends DomainObject, ID extends Serializable> {
	
	D create(D domainObject);
	
	D update(D domainObject) throws MissingIdException;
	
	void delete(ID id) throws NullParameterException, NotDeletedObjectException, NoResultFoundException;

	D find(ID id);
}
