package br.ufpr.services;

import br.ufpr.domain.Aluno;
import br.ufpr.domain.Pessoa;
import br.ufpr.exception.NoResultFoundException;

public interface AlunoService extends CrudService<Aluno, Integer> {

	Aluno findByPessoa(Pessoa pessoa) throws NoResultFoundException;
	
}
