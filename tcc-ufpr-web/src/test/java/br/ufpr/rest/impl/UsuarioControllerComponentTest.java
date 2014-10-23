package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.model.Usuario;
import br.ufpr.repository.UsuarioRepository;
import br.ufpr.support.PessoaTestSupport;

public class UsuarioControllerComponentTest extends PessoaTestSupport {

	@Autowired
	private UsuarioController usuarioController;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnCreate() throws MissingIdException, NullParameterException {
//		When
		usuarioController.create(null);
		
//		Then exception
	}
	
	@Test
	public void shouldInsertNewValidUsuario() throws NullParameterException {
//		Given
		Usuario usuario = new Usuario();
		usuario.setNome("fake name");
		usuario.setCpf("123456789-00");
		usuario.setLogin("login");
		usuario.setSenha("senha");
		
//		When
		Usuario savedUsuario = usuarioController.create(usuario);
		
//		Then
		assertNotNull(savedUsuario);
		assertNotNull(savedUsuario.getId());
		assertEquals(usuario.getNome(), savedUsuario.getNome());
		assertEquals(usuario.getCpf(), savedUsuario.getCpf());
		assertEquals(usuario.getLogin(), savedUsuario.getLogin());
		assertEquals(usuario.getSenha(), savedUsuario.getSenha());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnUpdate() throws MissingIdException, NullParameterException {
//		When
		usuarioController.update(null);
		
//		Then exception
	}
	
	@Test
	public void shouldUpdateExistentUsuario() throws MissingIdException, NullParameterException {
//		Given
		br.ufpr.domain.Usuario usuarioDomain = createAndSaveUsuario();
		Usuario usuario = new Usuario();
		usuario.setPessoaId(usuarioDomain.getPessoa().getId());
		usuario.setId(usuarioDomain.getId());
		usuario.setLogin("test");
		
//		When
		Usuario savedUsuario = usuarioController.update(usuario);
		
//		Then
		assertNotNull(savedUsuario);
		assertNotNull(savedUsuario.getId());
		assertEquals(usuario.getLogin(), savedUsuario.getLogin());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnDelete() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		usuarioController.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Usuario usuario = new Usuario();
		usuario.setId(1);
		
//		When
		usuarioController.delete(usuario);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentUsuario() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		br.ufpr.domain.Usuario usuarioDomain = createAndSaveUsuario();
		Usuario usuario = new Usuario();
		usuario.setPessoaId(usuarioDomain.getPessoa().getId());
		usuario.setId(usuarioDomain.getId());
		
//		When
		usuarioController.delete(usuario);
		
//		Then
		br.ufpr.domain.Usuario deletedUsuario = usuarioRepository.findOne(usuario.getId());
		assertTrue(deletedUsuario.isDeleted());
		assertNull(deletedUsuario.getAcessos());
	}
	
	private br.ufpr.domain.Usuario createAndSaveUsuario() {
		br.ufpr.domain.Usuario usuario = new br.ufpr.domain.Usuario();
		usuario.setPessoa(savedPessoa);
		usuario.setLogin("none");
		usuarioRepository.saveAndFlush(usuario);
		return usuario;
	}
	
}
