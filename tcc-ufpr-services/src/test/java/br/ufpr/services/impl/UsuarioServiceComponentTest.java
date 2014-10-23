package br.ufpr.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Usuario;
import br.ufpr.exception.MissingIdException;
import br.ufpr.repository.UsuarioRepository;
import br.ufpr.support.PessoaTestSupport;

public class UsuarioServiceComponentTest extends PessoaTestSupport {

	@Autowired
	private UsuarioService usuarioService;
	
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
	
	private Usuario createAndSaveUsuario() {
		Usuario usuario = new Usuario();
		usuario.setPessoa(savedPessoa);
		usuario.setAcessos("none");
		usuarioRepository.saveAndFlush(usuario);
		return usuario;
	}
	
}
