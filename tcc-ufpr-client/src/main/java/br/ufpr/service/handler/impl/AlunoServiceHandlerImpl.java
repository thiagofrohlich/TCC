package br.ufpr.service.handler.impl;

import org.springframework.stereotype.Service;

import br.ufpr.model.Aluno;
import br.ufpr.service.handler.AlunoServiceHandler;
import br.ufpr.wrapper.AlunoWrapper;
import br.ufpr.wrapper.Wrapper;

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

	@SuppressWarnings("unchecked")
	@Override
	public AlunoWrapper findByNome(String nome) {
		return (AlunoWrapper) getRestTemplate().getForObject(getPath()+"/nome/{nome}", AlunoWrapper.class, nome);

	}


}
