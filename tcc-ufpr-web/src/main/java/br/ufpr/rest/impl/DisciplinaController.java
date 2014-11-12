package br.ufpr.rest.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufpr.domain.Professor;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.model.Disciplina;
import br.ufpr.rest.AbstractRestController;
import br.ufpr.services.CrudService;
import br.ufpr.util.AssertUtils;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController extends AbstractRestController<Disciplina, br.ufpr.domain.Disciplina, Integer> {


	private CrudService<Professor, Integer> professorService;

	@Autowired
	public DisciplinaController(Mapper mapper, CrudService<br.ufpr.domain.Disciplina, Integer> disciplinaService, CrudService<Professor, Integer> professorService) {
		super(mapper, disciplinaService);
		this.professorService = professorService;
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Disciplina create(@RequestBody Disciplina model) throws NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		AssertUtils.assertParameterIsSupplied(model.getProfessor(), "Professor não pode ser nulo");
		br.ufpr.domain.Disciplina domain = mapper.map(model, br.ufpr.domain.Disciplina.class);
		domain.setProfessor(professorService.find(model.getProfessor().getId()));
		domain = service.create(domain);
		
		return mapToModel(domain);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public Disciplina update(@RequestBody Disciplina model) throws MissingIdException, NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		AssertUtils.assertParameterIsSupplied(model.getProfessor(), "Professor não pode ser nulo");
		br.ufpr.domain.Disciplina domain = mapper.map(model, br.ufpr.domain.Disciplina.class);
		domain.setProfessor(professorService.find(model.getProfessor().getId()));
		domain = service.update(domain);
		
		return mapToModel(domain);
	}

	@Override
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete(@RequestBody Disciplina model) throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Disciplina domain = mapper.map(model, br.ufpr.domain.Disciplina.class);
		service.delete(domain.getId());
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Disciplina find(@PathVariable final Integer id) throws NullParameterException,
			NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(id);
		br.ufpr.domain.Disciplina domain = service.find(id);
		AssertUtils.assertIsFound(domain);
		return mapToModel(domain);
	}
	
	private Disciplina mapToModel(br.ufpr.domain.Disciplina disciplinaDomain) {
		Disciplina disciplina = mapper.map(disciplinaDomain, Disciplina.class);
		return disciplina;
	}
	
}
