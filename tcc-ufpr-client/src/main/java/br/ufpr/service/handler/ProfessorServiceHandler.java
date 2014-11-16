package br.ufpr.service.handler;

import java.util.List;

import br.ufpr.model.Professor;

public interface ProfessorServiceHandler extends ServiceHandler<Professor, Integer>{

	Professor findByCpf(String cpf);
	
	List<Professor> findByNome(String nome);
	
}
