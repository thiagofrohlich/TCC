package br.ufpr.rest.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufpr.exception.MissingIdException;
import br.ufpr.model.Pessoa;
import br.ufpr.rest.AbstractRestController;
import br.ufpr.services.CrudService;

@Controller
@RequestMapping("/pessoa")
@Deprecated
public abstract class PessoaController extends AbstractRestController<Pessoa, br.ufpr.domain.Pessoa, Integer> {

	@Autowired
	public PessoaController(Mapper mapper, CrudService<br.ufpr.domain.Pessoa, Integer> pessoaService) {
		super(mapper, pessoaService);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Pessoa create(@RequestBody Pessoa model) {
		
		br.ufpr.domain.Pessoa domain = mapper.map(model, br.ufpr.domain.Pessoa.class);
		
		domain = crudService.create(domain);
		
		return mapper.map(domain, Pessoa.class);
	}
	
	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Pessoa update(@RequestBody Pessoa model) throws MissingIdException {
		
		br.ufpr.domain.Pessoa domain = mapper.map(model, br.ufpr.domain.Pessoa.class);
		
		domain = crudService.update(domain);
		
		return mapper.map(domain, Pessoa.class);
	}

}
