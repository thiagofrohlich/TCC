package br.ufpr.service.handler.impl;

import org.springframework.stereotype.Service;

import br.ufpr.model.Disciplina;
import br.ufpr.service.handler.DisciplinaServiceHandler;

@Service
public class DisciplinaServiceHandlerImpl extends AbstractServiceHandler<Disciplina, Integer> implements DisciplinaServiceHandler {

	@Override
	public String getRelativePath() {
		return "/disciplina";
	}

}
