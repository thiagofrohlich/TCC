package br.ufpr.services;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.domain.DomainObject;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.util.AssertUtils;

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
		AssertUtils.assertHasId(domainObject);
		return repository.saveAndFlush(domainObject);
	}
	
	@Override
	public void delete(ID id) throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(id);
		
		D domainObject = find(id);
		AssertUtils.assertIsFound(domainObject);
		
		try {
			domainObject.delete();
			domainObject = repository.saveAndFlush(domainObject);
			AssertUtils.assertIsDeleted(domainObject);
		} catch(Exception e) {
			throw new NotDeletedObjectException(e);
		}
		
	}

	@Override
	public D find(ID id) {
		return repository.findOne(id);
	}

}
