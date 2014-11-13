package br.ufpr.service.handler.impl;

import org.springframework.stereotype.Service;

import br.ufpr.model.Aluno;
import br.ufpr.model.Professor;
import br.ufpr.service.handler.AlunoServiceHandler;

@Service
public class AlunoServiceHandlerImpl extends AbstractServiceHandler<Aluno, Integer> implements AlunoServiceHandler {

	@Override
	public String getRelativePath() {
		return "/aluno";
	}

	public Aluno getByCpf(String cpf) {
		return  (Aluno) getRestTemplate().getForObject(getPath()+"/{cpf}", Aluno.class, cpf);
	}
	
}
