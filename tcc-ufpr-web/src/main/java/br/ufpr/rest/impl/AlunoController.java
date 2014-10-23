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
import br.ufpr.model.Aluno;
import br.ufpr.services.CrudService;

@Controller
@RequestMapping("/aluno")
public class AlunoController extends AbstractPessoaController<Aluno, br.ufpr.domain.Aluno, Integer> {


	@Autowired
	public AlunoController(Mapper mapper, CrudService<br.ufpr.domain.Aluno, Integer> alunoService, CrudService<Pessoa, Integer> pessoaService) {
		super(mapper, alunoService, pessoaService);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Aluno create(@RequestBody Aluno model) {
		br.ufpr.domain.Aluno domain = mapper.map(model, br.ufpr.domain.Aluno.class);
		domain.setPessoa(createPessoa(model));
		domain = crudService.create(domain);
		
		return mapToModel(domain);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public Aluno update(@RequestBody Aluno model) throws MissingIdException {
		br.ufpr.domain.Aluno domain = mapper.map(model, br.ufpr.domain.Aluno.class);
		domain.setPessoa(findPessoa(model.getPessoaId())); //find pessoa
		domain = crudService.update(domain);
		
		return mapToModel(domain);
	}

	private Aluno mapToModel(br.ufpr.domain.Aluno alunoDomain) {
		Aluno aluno = mapper.map(alunoDomain, Aluno.class);
		mapper.map(alunoDomain.getPessoa(), aluno);
		return aluno;
	}
	
}
