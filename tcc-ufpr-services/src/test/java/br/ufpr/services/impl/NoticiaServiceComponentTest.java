package br.ufpr.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Noticia;
import br.ufpr.domain.Pessoa;
import br.ufpr.domain.Usuario;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.repository.NoticiaRepository;
import br.ufpr.repository.PessoaRepository;
import br.ufpr.repository.UsuarioRepository;
import br.ufpr.services.CrudService;
import br.ufpr.support.SpringTestSupport;

public class NoticiaServiceComponentTest extends SpringTestSupport {

	@Autowired
	private CrudService<Noticia, Integer> noticiaService;
	
	@Autowired
	private NoticiaRepository noticiaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void shouldInsertNewValidNoticia() {
//		Given
		Noticia noticia = new Noticia();
		noticia.setTitulo("title");
		noticia.setConteudo("content");
		noticia.setUsuario(createUsuario());
		
//		When
		Noticia savedNoticia = noticiaService.create(noticia);
		
//		Then
		assertNotNull(savedNoticia);
		assertNotNull(savedNoticia.getId());
		assertEquals(noticia.getTitulo(), savedNoticia.getTitulo());
		assertEquals(noticia.getConteudo(), savedNoticia.getConteudo());
		assertEquals(noticia.getUsuario().getId(), savedNoticia.getUsuario().getId());
	}
	
	@Test
	public void shouldUpdateExistentNoticia() throws MissingIdException {
//		Given
		Noticia noticia = createAndSaveNoticia();
		noticia.setConteudo("modified content");
		
//		When
		Noticia savedNoticia = noticiaService.update(noticia);
		
//		Then
		assertNotNull(savedNoticia);
		assertNotNull(savedNoticia.getId());
		assertEquals(noticia.getConteudo(), savedNoticia.getConteudo());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameter() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		noticiaService.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		noticiaService.delete(1);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentNoticia() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Noticia noticia = createAndSaveNoticia();
		
//		When
		noticiaService.delete(noticia.getId());
		
//		Then
		Noticia deletedNoticia = noticiaRepository.findOne(noticia.getId());
		assertNull(deletedNoticia);
	}
	
	@Test
	public void shouldFindNoticiaGivenValidId() {
//		Given
		Noticia noticia = createAndSaveNoticia();
		
//		When
		Noticia noticiaFound = noticiaService.find(noticia.getId());
		
//		Then
		assertNotNull(noticiaFound);
		assertSame(noticia, noticiaFound);
	}
	
	private Noticia createAndSaveNoticia() {
		Noticia noticia = new Noticia();
		noticia.setTitulo("title");
		noticia.setConteudo("content");
		noticia.setUsuario(createUsuario());
		noticiaRepository.saveAndFlush(noticia);
		return noticia;
	}
	
	private Usuario createUsuario() {
		Usuario usuario = new Usuario();
		usuario.setPessoa(createPessoa());
		usuario.setLogin("login");
		usuarioRepository.saveAndFlush(usuario);
		return usuario;
	}
	
	private Pessoa createPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("fake name");
		pessoa.setCpf("123456789-00");
		return pessoaRepository.saveAndFlush(pessoa);
	}
	
}
