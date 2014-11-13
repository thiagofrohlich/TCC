package br.ufpr.service.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufpr.model.Professor;
import br.ufpr.service.handler.ProfessorServiceHandler;

@Service
public class ProfessorServiceHandlerImpl extends AbstractServiceHandler<Professor, Integer> implements ProfessorServiceHandler {

	@Override
	public String getRelativePath() {
		return "/professor";
	}
	
	public List<Professor> getLista(String nome) {
		return  (List<Professor>) getRestTemplate().getForObject(getPath()+"/{nome}", Professor.class, nome);
	}
	
	public Professor getByCpf(String cpf) {
		return  (Professor) getRestTemplate().getForObject(getPath()+"/{cpf}", Professor.class, cpf);
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
