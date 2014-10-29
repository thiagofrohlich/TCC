package br.ufpr.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpr.domain.Professor;
import br.ufpr.repository.ProfessorRepository;
import br.ufpr.services.AbstractCrudService;

@Service
@Transactional
public class ProfessorService extends AbstractCrudService<Professor, Integer> {

	@Autowired
	public ProfessorService(ProfessorRepository repository) {
		super(repository);
	}

}
