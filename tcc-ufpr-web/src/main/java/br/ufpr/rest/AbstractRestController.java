package br.ufpr.rest;

import org.dozer.Mapper;

import br.ufpr.domain.DomainObject;
import br.ufpr.model.BusinessModel;
import br.ufpr.services.CrudService;

public abstract class AbstractRestController<M extends BusinessModel, D extends DomainObject> implements RestController<M> {

	protected Mapper mapper;
	protected CrudService<D> crudService;

	public AbstractRestController(Mapper mapper, CrudService<D> crudService) {
		this.mapper = mapper;
		this.crudService = crudService;
		
	}
	
}
