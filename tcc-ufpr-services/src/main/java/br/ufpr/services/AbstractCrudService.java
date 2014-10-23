package br.ufpr.services;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.domain.DomainObject;
import br.ufpr.exception.MissingIdException;

public abstract class AbstractCrudService<D extends DomainObject, ID extends Serializable> implements CrudService<D, ID> {

	protected JpaRepository<D, ID> repository;

	public AbstractCrudService(JpaRepository<D, ID> repository) {
		this.repository = repository;
		
	}
	
	@Override
	public D create(D domainObject) {
		return repository.saveAndFlush(domainObject);
	}
	
	@Override
	public D update(D domainObject) throws MissingIdException {
		assertHasId(domainObject);
		return repository.saveAndFlush(domainObject);
	}

	private void assertHasId(D domainObject) throws MissingIdException {
		if(domainObject.getId() == null) {
			throw new MissingIdException();
		}
	}

	@Override
	public D find(ID id) {
		return repository.findOne(id);
	}

}
