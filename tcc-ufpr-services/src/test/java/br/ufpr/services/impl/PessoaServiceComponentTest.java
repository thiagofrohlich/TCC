package br.ufpr.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Pessoa;
import br.ufpr.support.SpringTestSupport;

public class PessoaServiceComponentTest extends SpringTestSupport {

	@Autowired
	private PessoaService pessoaService;
	
	@Test
	public void shouldInsertNewValidPessoa() {
//		Given
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("fake name");
		pessoa.setCpf("123456789-00");
		
//		When
		Pessoa savedPessoa = pessoaService.create(pessoa);
		
//		Then
		assertNotNull(savedPessoa);
		assertNotNull(savedPessoa.getId());
		assertEquals(pessoa.getNome(), savedPessoa.getNome());
		assertEquals(pessoa.getCpf(), savedPessoa.getCpf());
	}
	
}
