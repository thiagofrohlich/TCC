package br.ufpr.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Pessoa;
import br.ufpr.support.SpringTestSupport;

public class PessoaRepositoryTest extends SpringTestSupport {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void shouldInsertNewValidPessoa() {
//		Given
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("fake name");
		pessoa.setCpf("123456789-00");
		
//		When
		Pessoa savedPessoa = pessoaRepository.save(pessoa);
		
//		Then
		assertNotNull(savedPessoa);
		assertNotNull(savedPessoa.getId());
		assertEquals(pessoa.getNome(), savedPessoa.getNome());
		assertEquals(pessoa.getCpf(), savedPessoa.getCpf());
	}
	
}
