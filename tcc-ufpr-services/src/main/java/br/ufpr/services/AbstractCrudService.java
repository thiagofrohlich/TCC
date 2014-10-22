package br.ufpr.services;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.domain.DomainObject;

public abstract class AbstractCrudService<D extends DomainObject, ID extends Serializable> implements CrudService<D> {

	protected JpaRepository<D, ID> repository;

	public AbstractCrudService(JpaRepository<D, ID> repository) {
		this.repository = repository;
		
	}
	
	@Override
	public D create(D domainObject) {
		return repository.saveAndFlush(domainObject);
	}

}
