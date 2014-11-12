package br.ufpr.services;

import br.ufpr.domain.Pessoa;
import br.ufpr.domain.Professor;
import br.ufpr.exception.NoResultFoundException;

public interface ProfessorService extends CrudService<Professor, Integer> {

	Professor findByPessoa(Pessoa pessoa) throws NoResultFoundException;
	
}
