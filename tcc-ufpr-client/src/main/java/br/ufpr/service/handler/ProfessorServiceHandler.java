package br.ufpr.service.handler;

import br.ufpr.model.Professor;
import br.ufpr.wrapper.ProfessorWrapper;

public interface ProfessorServiceHandler extends ServiceHandler<Professor, Integer>{

	Professor findByCpf(String cpf);
	
	ProfessorWrapper findByNome(String nome);
	
}
