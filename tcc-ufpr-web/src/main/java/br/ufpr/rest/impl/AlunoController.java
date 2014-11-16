package br.ufpr.rest.impl;

import java.util.ArrayList;
import java.util.List;

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
import br.ufpr.model.Aluno;
import br.ufpr.services.AlunoService;
import br.ufpr.services.PessoaService;
import br.ufpr.util.AssertUtils;

@Controller
@RequestMapping("/aluno")
public class AlunoController extends AbstractPessoaController<Aluno, br.ufpr.domain.Aluno, Integer> {


	@Autowired
	public AlunoController(Mapper mapper, AlunoService alunoService, PessoaService pessoaService) {
		super(mapper, alunoService, pessoaService);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Aluno create(@RequestBody Aluno model) throws NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Aluno domain = mapper.map(model, br.ufpr.domain.Aluno.class);
		domain.setPessoa(createPessoa(model));
		domain = service.create(domain);
		
		return mapToModel(domain);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public Aluno update(@RequestBody Aluno model) throws MissingIdException, NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Aluno domain = mapper.map(model, br.ufpr.domain.Aluno.class);
		domain.setPessoa(findPessoa(model.getPessoaId()));
		domain = service.update(domain);
		
		return mapToModel(domain);
	}

	@Override
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete(@RequestBody Aluno model) throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Aluno domain = mapper.map(model, br.ufpr.domain.Aluno.class);
		service.delete(domain.getId());
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Aluno find(@PathVariable final Integer id) throws NullParameterException,
			NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(id);
		br.ufpr.domain.Aluno domain = service.find(id);
		AssertUtils.assertIsFound(domain);
		return mapToModel(domain);
	}
	
	@ResponseBody
	@RequestMapping(value="/cpf/{cpf}", method=RequestMethod.GET)
	public Aluno findByCpf(@PathVariable final String cpf) throws NullParameterException,
	NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(cpf);
		Pessoa pessoa = findPessoaByCpf(cpf);
		AssertUtils.assertIsFound(pessoa);
		return mapToModel(findOrCreateAlunoForPessoa(pessoa));
	}
	
	@ResponseBody
	@RequestMapping(value="/nome/{nome}", method=RequestMethod.GET)
	public List<Aluno> findByNome(@PathVariable final String nome) throws NullParameterException,
	NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(nome);
		List<Pessoa> pessoas = findPessoaByNome(nome);
		AssertUtils.assertIsFound(pessoas);
		return createReturnList(pessoas);
	}

	private List<Aluno> createReturnList(List<Pessoa> pessoas)
			throws NoResultFoundException {
		List<Aluno> returnList = new ArrayList<>(pessoas.size());
		for(Pessoa pessoa : pessoas) {
			returnList.add(mapToModel(findOrCreateAlunoForPessoa(pessoa)));
		}
		return returnList;
	}
	
	private br.ufpr.domain.Aluno findOrCreateAlunoForPessoa(Pessoa pessoa) throws NoResultFoundException {
		br.ufpr.domain.Aluno aluno;
		try {
			aluno = getService().findByPessoa(pessoa);
		} catch(NoResultFoundException e) {
			aluno = new br.ufpr.domain.Aluno();
			aluno.setPessoa(pessoa);
		}
		return aluno;
	}
	
	private Aluno mapToModel(br.ufpr.domain.Aluno alunoDomain) {
		Aluno aluno = mapper.map(alunoDomain, Aluno.class);
		mapper.map(alunoDomain.getPessoa(), aluno);
		return aluno;
	}
	
	private AlunoService getService() {
		return (AlunoService) service;
	}

}
