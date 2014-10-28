package br.ufpr.rest.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufpr.domain.Usuario;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.model.Noticia;
import br.ufpr.rest.AbstractRestController;
import br.ufpr.services.CrudService;
import br.ufpr.util.AssertUtils;

@Controller
@RequestMapping("/noticia")
public class NoticiaController extends AbstractRestController<Noticia, br.ufpr.domain.Noticia, Integer> {


	private CrudService<Usuario, Integer> usuarioService;

	@Autowired
	public NoticiaController(Mapper mapper, CrudService<br.ufpr.domain.Noticia, Integer> noticiaService, CrudService<Usuario, Integer> professorService) {
		super(mapper, noticiaService);
		this.usuarioService = professorService;
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Noticia create(@RequestBody Noticia model) throws NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		AssertUtils.assertParameterIsSupplied(model.getUsuario(), "Professor não pode ser nulo");
		br.ufpr.domain.Noticia domain = mapper.map(model, br.ufpr.domain.Noticia.class);
		domain.setUsuario(usuarioService.find(model.getUsuario().getId()));
		domain = crudService.create(domain);
		
		return mapToModel(domain);
	}

	@Override
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public Noticia update(@RequestBody Noticia model) throws MissingIdException, NullParameterException {
		AssertUtils.assertParameterIsSupplied(model);
		AssertUtils.assertParameterIsSupplied(model.getUsuario(), "Professor não pode ser nulo");
		br.ufpr.domain.Noticia domain = mapper.map(model, br.ufpr.domain.Noticia.class);
		domain.setUsuario(usuarioService.find(model.getUsuario().getId()));
		domain = crudService.update(domain);
		
		return mapToModel(domain);
	}

	@Override
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete(@RequestBody Noticia model) throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(model);
		br.ufpr.domain.Noticia domain = mapper.map(model, br.ufpr.domain.Noticia.class);
		crudService.delete(domain.getId());
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Noticia find(@PathVariable final Integer id) throws NullParameterException,
			NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(id);
		br.ufpr.domain.Noticia domain = crudService.find(id);
		AssertUtils.assertIsFound(domain);
		return mapToModel(domain);
	}
	
	private Noticia mapToModel(br.ufpr.domain.Noticia noticiaDomain) {
		Noticia noticia = mapper.map(noticiaDomain, Noticia.class);
		return noticia;
	}
	
}
