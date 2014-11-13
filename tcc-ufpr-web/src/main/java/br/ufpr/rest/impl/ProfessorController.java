package br.ufpr.rest.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import br.ufpr.services.PessoaService;
import br.ufpr.services.ProfessorService;
import br.ufpr.util.AssertUtils;

@Controller
@RequestMapping("/professor")
public class ProfessorController extends AbstractPessoaController<Professor, br.ufpr.domain.Professor, Integer> {

	@Autowired
	public ProfessorController(Mapper mapper, ProfessorService professorService, PessoaService pessoaService) {
		super(mapper, professorService, pessoaService);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Professor create(@RequestBody Professor model) throws NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Professor domain = mapper.map(model, br.ufpr.domain.Professor.class);
		domain.setPessoa(createPessoa(model));
		domain = service.create(domain);
		
		return mapToModel(domain);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public Professor update(@RequestBody Professor model) throws MissingIdException, NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Professor domain = mapper.map(model, br.ufpr.domain.Professor.class);
		domain.setPessoa(findPessoa(model.getPessoaId()));
		domain = service.update(domain);
		
		return mapToModel(domain);
	}
	
	@Override
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete(@RequestBody Professor model) throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Professor domain = mapper.map(model, br.ufpr.domain.Professor.class);
		service.delete(domain.getId());
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Professor find(@PathVariable final Integer id) throws NullParameterException,
			NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(id);
		br.ufpr.domain.Professor domain = service.find(id);
		AssertUtils.assertIsFound(domain);
		return mapToModel(domain);
	}
	
	@ResponseBody
	@RequestMapping(value="/cpf/{cpf}", method=RequestMethod.GET)
	public Professor findByCpf(@PathVariable final String cpf) throws NullParameterException,
	NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(cpf);
		Pessoa pessoa = findPessoaByCpf(cpf);
		AssertUtils.assertIsFound(pessoa);
		return mapToModel(findOrCreateProfessorForPessoa(pessoa));
	}
	
	@ResponseBody
	@RequestMapping(value="/nome/{nome}", method=RequestMethod.GET)
	public Professor findByNome(@PathVariable final String nome) throws NullParameterException,
	NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(nome);
		Pessoa pessoa = findPessoaByNome(nome);
		AssertUtils.assertIsFound(pessoa);
		return mapToModel(findOrCreateProfessorForPessoa(pessoa));
	}
	
	private br.ufpr.domain.Professor findOrCreateProfessorForPessoa(Pessoa pessoa) throws NoResultFoundException {
		br.ufpr.domain.Professor professor;
		try {
			professor = getService().findByPessoa(pessoa);
		} catch(NoResultFoundException e) {
			professor = new br.ufpr.domain.Professor();
			professor.setPessoa(pessoa);
		}
		return professor;
	}
	
	private Professor mapToModel(br.ufpr.domain.Professor professorDomain) {
		Professor professor = mapper.map(professorDomain, Professor.class);
		mapper.map(professorDomain.getPessoa(), professor);
		return professor;
	}

	private ProfessorService getService() {
		return (ProfessorService) service;
	}

}
