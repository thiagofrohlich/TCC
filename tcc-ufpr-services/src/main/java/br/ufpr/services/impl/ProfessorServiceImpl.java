package br.ufpr.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpr.domain.Pessoa;
import br.ufpr.domain.Professor;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.repository.ProfessorRepository;
import br.ufpr.services.AbstractCrudService;
import br.ufpr.services.ProfessorService;
import br.ufpr.util.AssertUtils;

@Service
@Transactional
public class ProfessorServiceImpl extends AbstractCrudService<Professor, Integer> implements ProfessorService {

	@Autowired
	public ProfessorServiceImpl(ProfessorRepository repository) {
		super(repository);
	}

	@Override
	public Professor findByPessoa(Pessoa pessoa) throws NoResultFoundException {
		Professor professor = getRepository().findByPessoa(pessoa);
		AssertUtils.assertIsFound(professor);
		return professor;
	}
	
	private ProfessorRepository getRepository() {
		return (ProfessorRepository) repository;
	}

}
