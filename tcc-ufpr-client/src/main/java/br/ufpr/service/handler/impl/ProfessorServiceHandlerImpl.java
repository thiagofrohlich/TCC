package br.ufpr.service.handler.impl;

import org.springframework.stereotype.Service;

import br.ufpr.model.Professor;
import br.ufpr.service.handler.ProfessorServiceHandler;

@Service
public class ProfessorServiceHandlerImpl extends AbstractServiceHandler<Professor, Integer> implements ProfessorServiceHandler {

	@Override
	public String getRelativePath() {
		return "/professor";
	}

	@Override
	public Professor findByCpf(String cpf) {
		return getRestTemplate().getForObject(getPath()+"/cpf/{cpf}", getReturnClass(), cpf);
	}

	@Override
	public Professor findByNome(String nome) {
		return getRestTemplate().getForObject(getPath()+"/nome/{nome}", getReturnClass(), nome);
	}

}
