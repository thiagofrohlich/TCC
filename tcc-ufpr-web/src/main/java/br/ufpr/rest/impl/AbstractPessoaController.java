package br.ufpr.rest.impl;

import java.io.Serializable;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.DomainObject;
import br.ufpr.domain.Pessoa;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.model.BusinessModel;
import br.ufpr.rest.AbstractRestController;
import br.ufpr.services.CrudService;
import br.ufpr.services.PessoaService;

public abstract class AbstractPessoaController<M extends BusinessModel, D extends DomainObject, ID extends Serializable> extends AbstractRestController<M, D, ID> {

	protected PessoaService pessoaService;

	@Autowired
	public AbstractPessoaController(Mapper mapper, CrudService<D, ID> service, PessoaService pessoaService) {
		super(mapper, service);
		this.pessoaService = pessoaService;
	}

	protected Pessoa createPessoa(br.ufpr.model.Pessoa model) {
		return pessoaService.create(mapper.map(model, Pessoa.class));
	}
	
	protected Pessoa findPessoa(Integer idPessoa) {
		return pessoaService.find(idPessoa);
	}
	
	protected Pessoa findPessoaByCpf(String cpf) throws NoResultFoundException {
		return pessoaService.findPessoaByCpf(cpf);
	}
	
	protected List<Pessoa> findPessoaByNome(String nome) throws NoResultFoundException {
		return pessoaService.findPessoaByNome(nome);
	}
	
}
