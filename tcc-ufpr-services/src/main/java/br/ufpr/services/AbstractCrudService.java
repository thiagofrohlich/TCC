package br.ufpr.services;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.domain.DomainObject;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;

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
	
	@Override
	public void delete(ID id) throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
		assertParameterIsSupplied(id);
		
		D domainObject = find(id);
		assertIsFound(domainObject);
		
		try {
			domainObject.delete();
			domainObject = repository.saveAndFlush(domainObject);
			assertIsDeleted(domainObject);
		} catch(Exception e) {
			throw new NotDeletedObjectException(e);
		}
		
	}

	@Override
	public D find(ID id) {
		return repository.findOne(id);
	}

	private void assertHasId(D domainObject) throws MissingIdException {
		if(domainObject.getId() == null) {
			throw new MissingIdException();
		}
	}

	private void assertParameterIsSupplied(ID id) throws NullParameterException {
		if(id == null) {
			throw new NullParameterException();
		}
	}

	private void assertIsDeleted(D domainObject) throws NotDeletedObjectException {
		if(!domainObject.isDeleted()) {
			throw new NotDeletedObjectException();
		}
	}

	private void assertIsFound(D domainObject) throws NoResultFoundException {
		if(domainObject == null) {
			throw new NoResultFoundException();
		}
	}

}
