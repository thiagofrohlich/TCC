package br.ufpr.service.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufpr.model.Professor;
import br.ufpr.service.handler.ProfessorServiceHandler;
import br.ufpr.wrapper.ProfessorWrapper;
import br.ufpr.wrapper.Wrapper;

@Service
public class ProfessorServiceHandlerImpl extends AbstractServiceHandler<Professor, Integer> implements ProfessorServiceHandler {

	@Override
	public String getRelativePath() {
		return "/professor";
	}
	
	public List<Professor> getLista(String nome) {
		return  (List<Professor>) getRestTemplate().getForObject(getPath()+"/{nome}", Professor.class, nome);
	}
	


	@Override
	public Professor findByCpf(String cpf) {
		return getRestTemplate().getForObject(getPath()+"/cpf/{cpf}", getReturnClass(), cpf);
	}

	@Override
	public ProfessorWrapper findByNome(String nome) {
		return (ProfessorWrapper) getRestTemplate().getForObject(getPath()+"/nome/{nome}", Wrapper.class, nome);
	}

}
