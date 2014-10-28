package br.ufpr.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Noticia;
import br.ufpr.domain.Usuario;
import br.ufpr.support.PessoaTestSupport;

public class NoticiaRepositoryTest extends PessoaTestSupport {
	
	@Autowired
	private NoticiaRepository noticiaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private Usuario usuario;
	
	@Before
	public void createUsuario() {
		Usuario user = new Usuario();
		user.setAcessos("none");
		user.setLogin("login");
		user.setPessoa(savedPessoa);
		usuario = usuarioRepository.saveAndFlush(user);
	}
	
	@Test
	public void shouldInsertNoticia() {
//		Given
		Noticia noticia = new Noticia();
		noticia.setTitulo("title");
		noticia.setConteudo("content");
		noticia.setUsuario(usuario);
		
//		When
		Noticia savedNoticia = noticiaRepository.saveAndFlush(noticia);
		
//		Then
		assertNotNull(savedNoticia);
		assertNotNull(savedNoticia.getId());
		assertSame(usuario, savedNoticia.getUsuario());
		assertEquals(noticia.getTitulo(), savedNoticia.getTitulo());
		assertEquals(noticia.getConteudo(), savedNoticia.getConteudo());
		
	}

}
