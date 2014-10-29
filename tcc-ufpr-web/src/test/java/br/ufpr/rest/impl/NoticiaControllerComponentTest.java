package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Pessoa;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.model.Noticia;
import br.ufpr.model.Usuario;
import br.ufpr.repository.NoticiaRepository;
import br.ufpr.repository.PessoaRepository;
import br.ufpr.repository.UsuarioRepository;
import br.ufpr.support.SpringTestSupport;

public class NoticiaControllerComponentTest extends SpringTestSupport {

	@Autowired
	private NoticiaController noticiaController;
	
	@Autowired
	private NoticiaRepository noticiaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnCreate() throws MissingIdException, NullParameterException {
//		When
		noticiaController.create(null);
		
//		Then exception
	}
	
	@Test
	public void shouldInsertNewValidNoticia() throws NullParameterException {
//		Given
		br.ufpr.domain.Usuario createdUsuario = createUsuario();
		Noticia noticia = new Noticia();
		noticia.setTitulo("title");
		noticia.setConteudo("content");
		noticia.setUsuario(new Usuario());
		noticia.getUsuario().setId(createdUsuario.getId());

//		When
		Noticia savedNoticia = noticiaController.create(noticia);
		
//		Then
		assertNotNull(savedNoticia);
		assertNotNull(savedNoticia.getId());
		assertEquals(noticia.getTitulo(), savedNoticia.getTitulo());
		assertEquals(noticia.getConteudo(), savedNoticia.getConteudo());
		assertEquals(noticia.getUsuario().getId(), savedNoticia.getUsuario().getId());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnUpdate() throws MissingIdException, NullParameterException {
//		When
		noticiaController.update(null);
		
//		Then exception
	}
	
	@Test
	public void shouldUpdateExistentNoticia() throws MissingIdException, NullParameterException {
//		Given
		br.ufpr.domain.Usuario createdUsuario = createUsuario();
		br.ufpr.domain.Noticia noticiaDomain = createAndSaveNoticia();
		Noticia noticia = new Noticia();
		noticia.setId(noticiaDomain.getId());
		noticia.setConteudo("modified content");
		noticia.setUsuario(new Usuario());
		noticia.getUsuario().setId(createdUsuario.getId());
		
//		When
		Noticia savedNoticia = noticiaController.update(noticia);
		
//		Then
		assertNotNull(savedNoticia);
		assertNotNull(savedNoticia.getId());
		assertEquals(noticia.getConteudo(), savedNoticia.getConteudo());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnDelete() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		noticiaController.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Noticia noticia = new Noticia();
		noticia.setId(1);
		
//		When
		noticiaController.delete(noticia);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentNoticia() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		br.ufpr.domain.Noticia noticiaDomain = createAndSaveNoticia();
		Noticia noticia = new Noticia();
		noticia.setId(noticiaDomain.getId());
		
//		When
		noticiaController.delete(noticia);
		
//		Then
		br.ufpr.domain.Noticia deletedNoticia = noticiaRepository.findOne(noticia.getId());
		assertNull(deletedNoticia);
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseExceptionGivenNullParameterSuppliedOnFind() throws NullParameterException, NoResultFoundException {
//		When
		noticiaController.find(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseExceptionGivenInvalidIdOnFind() throws NullParameterException, NoResultFoundException {
//		When
		noticiaController.find(1);
		
//		Then exception
	}
	
	@Test
	public void shouldFindNoticiaGivenValidId() throws NullParameterException, NoResultFoundException {
//		Given
		br.ufpr.domain.Noticia noticia = createAndSaveNoticia();
		
//		When
		Noticia noticiaFound = noticiaController.find(noticia.getId());
		
//		Then
		assertNotNull(noticiaFound);
		assertEquals(noticia.getId().intValue(), noticiaFound.getId().intValue());
		assertEquals(noticia.getTitulo(), noticiaFound.getTitulo());
		assertEquals(noticia.getConteudo(), noticiaFound.getConteudo());
		assertEquals(noticia.getUsuario().getId(), noticiaFound.getUsuario().getId());
	}
	
	private br.ufpr.domain.Noticia createAndSaveNoticia() {
		br.ufpr.domain.Noticia noticia = new br.ufpr.domain.Noticia();
		noticia.setTitulo("title");
		noticia.setConteudo("content");
		noticia.setUsuario(createUsuario());
		noticiaRepository.saveAndFlush(noticia);
		return noticia;
	}
	
	
	private br.ufpr.domain.Usuario createUsuario() {
		br.ufpr.domain.Usuario usuario = new br.ufpr.domain.Usuario();
		usuario.setPessoa(createPessoa());
		usuario.setLogin("login");
		usuarioRepository.saveAndFlush(usuario);
		return usuario;
	}
	
	private br.ufpr.domain.Pessoa createPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("fake name");
		pessoa.setCpf("123456789-00");
		return pessoaRepository.saveAndFlush(pessoa);
	}
	
}
