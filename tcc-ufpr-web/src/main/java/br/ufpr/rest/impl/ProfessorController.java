package br.ufpr.rest.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufpr.domain.Pessoa;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.model.Professor;
import br.ufpr.services.CrudService;
import br.ufpr.util.AssertUtils;

@Controller
@RequestMapping("/professor")
public class ProfessorController extends AbstractPessoaController<Professor, br.ufpr.domain.Professor, Integer> {

	@Autowired
	public ProfessorController(Mapper mapper, CrudService<br.ufpr.domain.Professor, Integer> professorService, CrudService<Pessoa, Integer> pessoaService) {
		super(mapper, professorService, pessoaService);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Professor create(@RequestBody Professor model) throws NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Professor domain = mapper.map(model, br.ufpr.domain.Professor.class);
		domain.setPessoa(createPessoa(model));
		domain = crudService.create(domain);
		
		return mapToModel(domain);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public Professor update(@RequestBody Professor model) throws MissingIdException, NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Professor domain = mapper.map(model, br.ufpr.domain.Professor.class);
		domain.setPessoa(findPessoa(model.getPessoaId()));
		domain = crudService.update(domain);
		
		return mapToModel(domain);
	}
	
	@Override
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete(@RequestBody Professor model) throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Professor domain = mapper.map(model, br.ufpr.domain.Professor.class);
		crudService.delete(domain.getId());
	}
	
	private Professor mapToModel(br.ufpr.domain.Professor professorDomain) {
		Professor professor = mapper.map(professorDomain, Professor.class);
		mapper.map(professorDomain.getPessoa(), professor);
		return professor;
	}

}
