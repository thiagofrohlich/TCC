package br.ufpr.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpr.domain.Pessoa;
import br.ufpr.repository.PessoaRepository;
import br.ufpr.services.AbstractCrudService;

@Service
@Transactional
public class PessoaService extends AbstractCrudService<Pessoa, Integer> {

	@Autowired
	public PessoaService(PessoaRepository repository) {
		super(repository);
	}

}
