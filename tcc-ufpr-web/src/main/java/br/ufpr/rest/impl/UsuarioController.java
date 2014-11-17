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
import br.ufpr.model.Usuario;
import br.ufpr.services.PessoaService;
import br.ufpr.services.UserService;
import br.ufpr.util.AssertUtils;
import br.ufpr.wrapper.UsuarioWrapper;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends AbstractPessoaController<Usuario, br.ufpr.domain.Usuario, Integer> {

	@Autowired
	public UsuarioController(Mapper mapper, UserService usuarioService, PessoaService pessoaService) {
		super(mapper, usuarioService, pessoaService);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Usuario create(@RequestBody Usuario model) throws NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Usuario domain = mapper.map(model, br.ufpr.domain.Usuario.class);
		domain.setPessoa(createPessoa(model));
		domain = service.create(domain);
		
		return mapToModel(domain);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public Usuario update(@RequestBody Usuario model) throws MissingIdException, NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Usuario domain = mapper.map(model, br.ufpr.domain.Usuario.class);
		domain.setPessoa(findPessoa(model.getPessoaId()));
		domain = service.update(domain);
		
		return mapToModel(domain);
	}
	
	@Override
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete(@RequestBody Usuario model) throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Usuario domain = mapper.map(model, br.ufpr.domain.Usuario.class);
		service.delete(domain.getId());
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Usuario find(@PathVariable final Integer id) throws NullParameterException,
			NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(id);
		br.ufpr.domain.Usuario domain = service.find(id);
		AssertUtils.assertIsFound(domain);
		return mapToModel(domain);
	}
	
	@RequestMapping(value="/password/encode/{password}", method=RequestMethod.GET)
	public String encodePassword(@PathVariable final String password) throws NullParameterException {
		AssertUtils.assertParameterIsSupplied(password);
		return getUserService().encodePassword(password);
	}
	
	@RequestMapping(value="/login/{login}/{password}", method=RequestMethod.GET)
	public boolean canLogin(@PathVariable final String login, @PathVariable final String password) {
		return getUserService().canLogin(login, password);
	}
	
	@ResponseBody
	@RequestMapping(value="/cpf/{cpf}", method=RequestMethod.GET)
	public Usuario findByCpf(@PathVariable final String cpf) throws NullParameterException,
	NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(cpf);
		Pessoa pessoa = findPessoaByCpf(cpf);
		AssertUtils.assertIsFound(pessoa);
		return mapToModel(findOrCreateUsuarioForPessoa(pessoa));
	}
	
	@ResponseBody
	@RequestMapping(value="/nome/{nome}", method=RequestMethod.GET)
	public UsuarioWrapper findByNome(@PathVariable final String nome) throws NullParameterException,
	NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(nome);
		List<Pessoa> pessoas = findPessoaByNome(nome);
		AssertUtils.assertIsFound(pessoas);
		return createReturnList(pessoas);
	}
	
	private UsuarioWrapper createReturnList(List<Pessoa> pessoas)
			throws NoResultFoundException {
		List<Usuario> returnList = new ArrayList<>(pessoas.size());
		for(Pessoa pessoa : pessoas) {
			returnList.add(mapToModel(findOrCreateUsuarioForPessoa(pessoa)));
		}
		return new UsuarioWrapper(returnList);
	}
	
	private br.ufpr.domain.Usuario findOrCreateUsuarioForPessoa(Pessoa pessoa) throws NoResultFoundException {
		br.ufpr.domain.Usuario usuario;
		try {
			usuario = getUserService().findByPessoa(pessoa);
		} catch(NoResultFoundException e) {
			usuario = new br.ufpr.domain.Usuario();
			usuario.setPessoa(pessoa);
		}
		return usuario;
	}
	
	private Usuario mapToModel(br.ufpr.domain.Usuario usuarioDomain) {
		Usuario usuario = mapper.map(usuarioDomain, Usuario.class);
		mapper.map(usuarioDomain.getPessoa(), usuario);
		return usuario;
	}
	
	private UserService getUserService() {
		return (UserService) service;
	}

}
