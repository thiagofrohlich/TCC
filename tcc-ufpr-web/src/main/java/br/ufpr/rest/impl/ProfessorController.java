package br.ufpr.rest.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufpr.domain.Pessoa;
import br.ufpr.model.Professor;
import br.ufpr.services.CrudService;

@Controller
@RequestMapping("/professor")
public class ProfessorController extends AbstractPessoaController<Professor, br.ufpr.domain.Professor> {

	@Autowired
	public ProfessorController(Mapper mapper, CrudService<br.ufpr.domain.Professor> professorService, CrudService<Pessoa> pessoaService) {
		super(mapper, professorService, pessoaService);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Professor create(@RequestBody Professor model) {
		
		br.ufpr.domain.Professor domain = mapper.map(model, br.ufpr.domain.Professor.class);
		domain.setPessoa(createPessoa(model));
		domain = crudService.create(domain);
		
		return mapToModel(domain);
	}

	private Professor mapToModel(br.ufpr.domain.Professor professorDomain) {
		Professor professor = mapper.map(professorDomain, Professor.class);
		mapper.map(professorDomain.getPessoa(), professor);
		return professor;
	}

}
