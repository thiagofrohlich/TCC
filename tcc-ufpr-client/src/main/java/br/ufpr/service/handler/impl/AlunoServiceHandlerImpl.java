package br.ufpr.service.handler.impl;

import org.springframework.stereotype.Service;

import br.ufpr.model.Aluno;
import br.ufpr.service.handler.AlunoServiceHandler;

@Service
public class AlunoServiceHandlerImpl extends AbstractServiceHandler<Aluno, Integer> implements AlunoServiceHandler {

	@Override
	public String getRelativePath() {
		return "/aluno";
	}

}
