package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.model.Usuario;
import br.ufpr.support.SpringTestSupport;

public class UsuarioControllerComponentTest extends SpringTestSupport {

	@Autowired
	private UsuarioController usuarioController;
	
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
	
}
