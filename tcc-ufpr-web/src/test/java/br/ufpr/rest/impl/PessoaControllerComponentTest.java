package br.ufpr.rest.impl;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.support.SpringTestSupport;

@Ignore
public class PessoaControllerComponentTest extends SpringTestSupport {

	@Autowired
	private PessoaController pessoaController;
	
/*	@Test
	public void shouldInsertNewValidPessoa() {
//		Given
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("fake name");
		pessoa.setCpf("123456789-00");
		
//		When
		Pessoa savedPessoa = pessoaController.create(pessoa);
		
//		Then
		assertNotNull(savedPessoa);
		assertNotNull(savedPessoa.getId());
		assertEquals(pessoa.getNome(), savedPessoa.getNome());
		assertEquals(pessoa.getCpf(), savedPessoa.getCpf());
	}*/
	
}
