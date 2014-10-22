package br.ufpr.rest.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.DomainObject;
import br.ufpr.domain.Pessoa;
import br.ufpr.model.BusinessModel;
import br.ufpr.rest.AbstractRestController;
import br.ufpr.services.CrudService;

public abstract class AbstractPessoaController<M extends BusinessModel, D extends DomainObject> extends AbstractRestController<M, D > {

	protected CrudService<br.ufpr.domain.Pessoa> pessoaService;

	@Autowired
	public AbstractPessoaController(Mapper mapper, CrudService<D> service, CrudService<br.ufpr.domain.Pessoa> pessoaService) {
		super(mapper, service);
		this.pessoaService = pessoaService;
	}

	protected Pessoa createPessoa(br.ufpr.model.Pessoa model) {
		return pessoaService.create(mapper.map(model, Pessoa.class));
	}
	
}
