package br.ufpr.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Usuario;
import br.ufpr.support.PessoaTestSupport;

public class UsuarioRepositoryTest extends PessoaTestSupport {

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
		Usuario savedUsuario = usuarioRepository.save(usuario);
		
//		Then
		assertNotNull(savedUsuario);
		assertNotNull(savedUsuario.getId());
		assertNotNull(savedUsuario.getPessoa());
		assertSame(usuario.getPessoa(), savedUsuario.getPessoa());
		assertEquals(usuario.getLogin(), savedUsuario.getLogin());
		assertEquals(usuario.getSenha(), savedUsuario.getSenha());
	}
	
	@Test
	public void shoulFindByLogin() {
//		Given
		Usuario user = createUser();
		
//		When
		Usuario userFound = usuarioRepository.findByLogin(user.getLogin());
		
//		Then
		assertNotNull(userFound);
		assertSame(user, userFound);
		
	}

	private Usuario createUser() {
		Usuario usuario = new Usuario();
		usuario.setPessoa(savedPessoa);
		usuario.setLogin("login");
		usuario.setSenha("senha");;
		return usuarioRepository.save(usuario);
	}
	
}
