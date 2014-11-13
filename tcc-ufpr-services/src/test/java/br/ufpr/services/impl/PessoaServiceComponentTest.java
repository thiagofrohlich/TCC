package br.ufpr.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Pessoa;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.repository.PessoaRepository;
import br.ufpr.services.PessoaService;
import br.ufpr.support.SpringTestSupport;

public class PessoaServiceComponentTest extends SpringTestSupport {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
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
	
	@Test
	public void shouldUpdateExistentPessoa() throws MissingIdException {
//		Given
		Pessoa pessoa = createAndSavePessoa();
		pessoa.setCidade("city");
		
//		When
		Pessoa savedPessoa = pessoaService.update(pessoa);
		
//		Then
		assertNotNull(savedPessoa);
		assertNotNull(savedPessoa.getId());
		assertEquals(pessoa.getCidade(), savedPessoa.getCidade());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameter() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		pessoaService.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		pessoaService.delete(1);
		
//		Then exception
	}
	
	@Test(expected=NotDeletedObjectException.class)
	public void shouldDeleteExistentPessoa() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Pessoa pessoa = createAndSavePessoa();
		
//		When
		pessoaService.delete(pessoa.getId());
		
//		Then exception
	}
	
	@Test
	public void shouldFindPessoaGivenValidId() {
//		Given
		Pessoa pessoa = createAndSavePessoa();
		
//		When
		Pessoa pessoaFound = pessoaService.find(pessoa.getId());
		
//		Then
		assertNotNull(pessoaFound);
		assertSame(pessoa, pessoaFound);
	}
	
	@Test
	public void shouldFindByCpf() throws NoResultFoundException {
//		Given
		Pessoa createdPessoa = createAndSavePessoa();
		
//		When
		Pessoa pessoaFound = pessoaService.findPessoaByCpf(createdPessoa.getCpf());
		
//		Then
		assertNotNull(pessoaFound);
		assertSame(pessoaFound, createdPessoa);
	}
	
	@Test(expected = NoResultFoundException.class)
	public void shouldNotFindByCpfGivenInvalidCpf() throws NoResultFoundException {
//		Given
		Pessoa createdPessoa = createAndSavePessoa();
		
//		When
		Pessoa pessoaFound = pessoaService.findPessoaByCpf(createdPessoa.getCpf() + "00");
		
//		Then
		assertNull(pessoaFound);
	}
	
	@Test
	public void shouldFindByExactNome() throws NoResultFoundException {
//		Given
		Pessoa createdPessoa = createAndSavePessoa();
		
//		When
		Pessoa pessoaFound = pessoaService.findPessoaByNome(createdPessoa.getNome());
		
//		Then
		assertNotNull(pessoaFound);
		assertSame(pessoaFound, createdPessoa);
	}
	
	@Test
	public void shouldFindByNomeContaining() throws NoResultFoundException {
//		Given
		Pessoa createdPessoa = createAndSavePessoa();
		
//		When
		Pessoa pessoaFound = pessoaService.findPessoaByNome("oa na");
		
//		Then
		assertNotNull(pessoaFound);
		assertSame(pessoaFound, createdPessoa);
	}
	
	@Test(expected = NoResultFoundException.class)
	public void shouldNotFindByNomeGivenInvalidNome() throws NoResultFoundException {
//		Given
		createAndSavePessoa();
		
//		When
		Pessoa pessoaFound = pessoaService.findPessoaByNome("invalid");
		
//		Then
		assertNull(pessoaFound);
	}

	private Pessoa createAndSavePessoa() {
		br.ufpr.domain.Pessoa pessoa = new br.ufpr.domain.Pessoa();
		pessoa.setNome("pessoa name");
		pessoa.setCidade("none");
		pessoaRepository.saveAndFlush(pessoa);
		return pessoa;
	}
	
}
