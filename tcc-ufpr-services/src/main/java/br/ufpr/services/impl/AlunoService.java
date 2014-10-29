package br.ufpr.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpr.domain.Aluno;
import br.ufpr.repository.AlunoRepository;
import br.ufpr.services.AbstractCrudService;

@Service
@Transactional
public class AlunoService extends AbstractCrudService<Aluno, Integer> {

	@Autowired
	public AlunoService(AlunoRepository repository) {
		super(repository);
	}

}
