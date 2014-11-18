package br.ufpr.service.handler;

import br.ufpr.model.Aluno;
import br.ufpr.wrapper.AlunoWrapper;

public interface AlunoServiceHandler extends ServiceHandler<Aluno, Integer>{

	Aluno findByCpf(String cpf);
	
	AlunoWrapper findByNome(String nome);
	
}
