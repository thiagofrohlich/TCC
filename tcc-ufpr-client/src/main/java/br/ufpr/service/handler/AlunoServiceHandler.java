package br.ufpr.service.handler;

import br.ufpr.model.Aluno;

public interface AlunoServiceHandler extends ServiceHandler<Aluno, Integer>{

	Aluno findByCpf(String cpf);
	
	Aluno findByNome(String nome);
	
}
