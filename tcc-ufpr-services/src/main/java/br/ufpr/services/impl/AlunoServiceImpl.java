package br.ufpr.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpr.domain.Aluno;
import br.ufpr.domain.Pessoa;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.repository.AlunoRepository;
import br.ufpr.services.AbstractCrudService;
import br.ufpr.services.AlunoService;
import br.ufpr.util.AssertUtils;

@Service
@Transactional
public class AlunoServiceImpl extends AbstractCrudService<Aluno, Integer> implements AlunoService {

	@Autowired
	public AlunoServiceImpl(AlunoRepository repository) {
		super(repository);
	}

	@Override
	public Aluno findByPessoa(Pessoa pessoa) throws NoResultFoundException {
		Aluno aluno = getRepository().findByPessoa(pessoa);
		AssertUtils.assertIsFound(aluno);
		return aluno;
	}
	
	private AlunoRepository getRepository() {
		return (AlunoRepository) repository;
	}

}
