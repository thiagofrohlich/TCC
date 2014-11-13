package br.ufpr.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Usuario;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.repository.UsuarioRepository;
import br.ufpr.services.UserService;
import br.ufpr.support.PessoaTestSupport;

public class UsuarioServiceComponentTest extends PessoaTestSupport {

	@Autowired
	private UserService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	public void shouldInsertNewValidUsuario() {
//		Given
		Usuario usuario = new Usuario();
		usuario.setPessoa(savedPessoa);
		usuario.setLogin("login");
		usuario.setSenha("senha");
		
//		When
		Usuario savedUsuario = usuarioService.create(usuario);
		
//		Then
		assertNotNull(savedUsuario);
		assertNotNull(savedUsuario.getId());
		assertNotNull(savedUsuario.getPessoa());
		assertSame(usuario.getPessoa(), savedUsuario.getPessoa());
		assertEquals(usuario.getLogin(), savedUsuario.getLogin());
		assertEquals(usuario.getSenha(), savedUsuario.getSenha());
	}
	
	@Test
	public void shouldUpdateExistentUsuario() throws MissingIdException {
//		Given
		Usuario usuario = createAndSaveUsuario();
		usuario.setAcessos("test");
		
//		When
		Usuario savedUsuario = usuarioService.update(usuario);
		
//		Then
		assertNotNull(savedUsuario);
		assertNotNull(savedUsuario.getId());
		assertEquals(usuario.getAcessos(), savedUsuario.getAcessos());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameter() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		usuarioService.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		usuarioService.delete(1);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentUsuario() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Usuario usuario = createAndSaveUsuario();
		
//		When
		usuarioService.delete(usuario.getId());
		
//		Then
		Usuario deletedUsuario = usuarioRepository.findOne(usuario.getId());
		assertTrue(deletedUsuario.isDeleted());
		assertNull(deletedUsuario.getAcessos());
	}
	
	@Test
	public void shouldFindAlunoGivenValidId() {
//		Given
		Usuario usuario = createAndSaveUsuario();
		
//		When
		Usuario usuarioFound = usuarioService.find(usuario.getId());
		
//		Then
		assertNotNull(usuarioFound);
		assertSame(usuario, usuarioFound);
	}
	
	@Test
	public void shouldEncodePassword() {
//		Given
		String password = "password";
		
//		When
		String encodedPassword = usuarioService.encodePassword(password);
		
//		Then
		assertNotNull(encodedPassword);
		assertEquals(usuarioService.encodePassword(password), encodedPassword);
	}
	
	@Test
	public void shouldNotLoginGivenNullLogin() {
//		Given
		String login = null;
		String password = "password";
		
//		When
		boolean canLogin = usuarioService.canLogin(login, password);
		
//		Then
		assertFalse(canLogin);
	}
	
	@Test
	public void shouldNotLoginGivenEmptyPassword() {
//		Given
		String login = "login";
		String password = "";
		
//		When
		boolean canLogin = usuarioService.canLogin(login, password);
		
//		Then
		assertFalse(canLogin);
	}
	
	@Test
	public void shouldNotLoginGivenWrongUser() {
//		Given
		Usuario usuario = createAndSaveUsuario();
		String login = usuario.getLogin() + "-wrong";
		String password = usuario.getSenha();
		
		usuario.setSenha(usuarioService.encodePassword(usuario.getSenha()));
		usuarioRepository.saveAndFlush(usuario);
		
//		When
		boolean canLogin = usuarioService.canLogin(login, password);
		
//		Then
		assertFalse(canLogin);
		
	}
	
	@Test
	public void shouldNotLoginGivenWrongPassword() {
//		Given
		Usuario usuario = createAndSaveUsuario();
		String login = usuario.getLogin();
		String password = usuario.getSenha() + "-wrong";
		
		usuario.setSenha(usuarioService.encodePassword(usuario.getSenha()));
		usuarioRepository.saveAndFlush(usuario);
		
//		When
		boolean canLogin = usuarioService.canLogin(login, password);
		
//		Then
		assertFalse(canLogin);
		
	}
	
	@Test
	public void shouldLogin() {
//		Given
		Usuario usuario = createAndSaveUsuario();
		String login = usuario.getLogin();
		String password = usuario.getSenha();
		
		usuario.setSenha(usuarioService.encodePassword(usuario.getSenha()));
		usuarioRepository.saveAndFlush(usuario);
		
//		When
		boolean canLogin = usuarioService.canLogin(login, password);
		
//		Then
		assertTrue(canLogin);
		
	}
	
	@Test
	public void shouldFindUsuarioByPessoa() throws NoResultFoundException {
//		Given
		Usuario usuario = createAndSaveUsuario();
		
//		When
		Usuario usuarioFound = usuarioService.findByPessoa(savedPessoa);
		
//		Then
		assertNotNull(usuarioFound);
		assertSame(usuario, usuarioFound);
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldNotFindUsuarioByPessoaGivenNotCreatedUsuario() throws NoResultFoundException {
//		When
		usuarioService.findByPessoa(savedPessoa);
		
//		Then exception
	}
	
	private Usuario createAndSaveUsuario() {
		Usuario usuario = new Usuario();
		usuario.setLogin("login");
		usuario.setSenha("password");
		usuario.setPessoa(savedPessoa);
		usuario.setAcessos("none");
		usuarioRepository.saveAndFlush(usuario);
		return usuario;
	}
	
}
