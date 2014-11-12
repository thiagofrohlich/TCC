package br.ufpr.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

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
	
	@Test
	public void shouldFindByCpf() {
//		Given
		Pessoa createdPessoa = createPessoa();
		
//		When
		Pessoa pessoaFound = pessoaRepository.findByCpf(createdPessoa.getCpf());
		
//		Then
		assertNotNull(pessoaFound);
		assertSame(pessoaFound, createdPessoa);
	}
	
	@Test
	public void shouldNotFindByCpfGivenInvalidCpf() {
//		Given
		Pessoa createdPessoa = createPessoa();
		
//		When
		Pessoa pessoaFound = pessoaRepository.findByCpf(createdPessoa.getCpf() + "00");
		
//		Then
		assertNull(pessoaFound);
	}
	
	@Test
	public void shouldFindByExactNome() {
//		Given
		Pessoa createdPessoa = createPessoa();
		
//		When
		Pessoa pessoaFound = pessoaRepository.findByNomeContaining(createdPessoa.getNome());
		
//		Then
		assertNotNull(pessoaFound);
		assertSame(pessoaFound, createdPessoa);
	}
	
	@Test
	public void shouldFindByNomeContaining() {
//		Given
		Pessoa createdPessoa = createPessoa();
		
//		When
		Pessoa pessoaFound = pessoaRepository.findByNomeContaining("oa na");
		
//		Then
		assertNotNull(pessoaFound);
		assertSame(pessoaFound, createdPessoa);
	}
	
	@Test
	public void shouldNotFindByNomeGivenInvalidNome() {
//		Given
		createPessoa();
		
//		When
		Pessoa pessoaFound = pessoaRepository.findByNomeContaining("invalid");
		
//		Then
		assertNull(pessoaFound);
	}

	private Pessoa createPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("1234567890");
		pessoa.setNome("pessoa name");
		return pessoaRepository.saveAndFlush(pessoa);
	}
	
}
