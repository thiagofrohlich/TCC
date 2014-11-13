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


	@Override
	public Aluno findByCpf(String cpf) {
		return getRestTemplate().getForObject(getPath()+"/cpf/{cpf}", getReturnClass(), cpf);
	}

	@Override
	public Aluno findByNome(String nome) {
		return getRestTemplate().getForObject(getPath()+"/nome/{nome}", getReturnClass(), nome);
	}


}
