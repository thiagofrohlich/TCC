package br.ufpr.service.handler;

import br.ufpr.model.Professor;

public interface ProfessorServiceHandler extends ServiceHandler<Professor, Integer>{

	Professor findByCpf(String cpf);
	
	Professor findByNome(String nome);
	
}
