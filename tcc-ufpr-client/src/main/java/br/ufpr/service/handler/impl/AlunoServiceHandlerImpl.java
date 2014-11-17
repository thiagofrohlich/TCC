package br.ufpr.service.handler.impl;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findByNome(String nome) {
		return (List<Aluno>) getRestTemplate().getForObject(getPath()+"/nome/{nome}", List.class, nome);
	}


}
