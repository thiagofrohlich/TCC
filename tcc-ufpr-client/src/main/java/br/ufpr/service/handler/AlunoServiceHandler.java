package br.ufpr.service.handler;

import java.util.List;

import br.ufpr.model.Aluno;

public interface AlunoServiceHandler extends ServiceHandler<Aluno, Integer>{

	Aluno findByCpf(String cpf);
	
	List<Aluno> findByNome(String nome);
	
}
