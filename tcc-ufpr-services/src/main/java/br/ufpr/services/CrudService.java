package br.ufpr.services;

import br.ufpr.domain.DomainObject;

public interface CrudService<D extends DomainObject> {
	
	D create(D domainObject);

}
