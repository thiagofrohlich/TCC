package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.exception.MissingIdException;
import br.ufpr.model.Usuario;
import br.ufpr.repository.UsuarioRepository;
import br.ufpr.support.PessoaTestSupport;

public class UsuarioControllerComponentTest extends PessoaTestSupport {

	@Autowired
	private UsuarioController usuarioController;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void shouldInsertNewValidUsuario() {
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
	
	@Test
	public void shouldUpdateExistentUsuario() throws MissingIdException {
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
	
	private br.ufpr.domain.Usuario createAndSaveUsuario() {
		br.ufpr.domain.Usuario usuario = new br.ufpr.domain.Usuario();
		usuario.setPessoa(savedPessoa);
		usuario.setLogin("none");
		usuarioRepository.saveAndFlush(usuario);
		return usuario;
	}
	
}
