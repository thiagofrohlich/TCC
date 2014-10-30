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

}
