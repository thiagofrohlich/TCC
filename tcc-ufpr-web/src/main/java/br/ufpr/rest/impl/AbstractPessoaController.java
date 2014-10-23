package br.ufpr.rest.impl;

import java.io.Serializable;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.DomainObject;
import br.ufpr.domain.Pessoa;
import br.ufpr.model.BusinessModel;
import br.ufpr.rest.AbstractRestController;
import br.ufpr.services.CrudService;

public abstract class AbstractPessoaController<M extends BusinessModel, D extends DomainObject, ID extends Serializable> extends AbstractRestController<M, D, ID> {

	protected CrudService<br.ufpr.domain.Pessoa, Integer> pessoaService;

	@Autowired
	public AbstractPessoaController(Mapper mapper, CrudService<D, ID> service, CrudService<br.ufpr.domain.Pessoa, Integer> pessoaService) {
		super(mapper, service);
		this.pessoaService = pessoaService;
	}

	protected Pessoa createPessoa(br.ufpr.model.Pessoa model) {
		return pessoaService.create(mapper.map(model, Pessoa.class));
	}
	
	protected Pessoa findPessoa(Integer idPessoa) {
		return pessoaService.find(idPessoa);
	}
	
}
