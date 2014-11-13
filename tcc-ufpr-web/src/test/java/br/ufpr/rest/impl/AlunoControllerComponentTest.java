package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.model.Aluno;
import br.ufpr.repository.AlunoRepository;
import br.ufpr.support.PessoaTestSupport;

public class AlunoControllerComponentTest extends PessoaTestSupport {

	@Autowired
	private AlunoController alunoController;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnCreate() throws MissingIdException, NullParameterException {
//		When
		alunoController.create(null);
		
//		Then exception
	}
	
	@Test
	public void shouldInsertNewValidAluno() throws NullParameterException {
//		Given
		Aluno aluno = new Aluno();
		aluno.setNome("fake name");
		aluno.setCpf("123456789-00");
		
//		When
		Aluno savedAluno = alunoController.create(aluno);
		
//		Then
		assertNotNull(savedAluno);
		assertNotNull(savedAluno.getMatricula());
		assertEquals(aluno.getNome(), savedAluno.getNome());
		assertEquals(aluno.getCpf(), savedAluno.getCpf());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnUpdate() throws MissingIdException, NullParameterException {
//		When
		alunoController.update(null);
		
//		Then exception
	}
	
	@Test
	public void shouldUpdateExistentAluno() throws MissingIdException, NullParameterException {
//		Given
		br.ufpr.domain.Aluno alunoDomain = createAndSaveAluno(false);
		Aluno aluno = new Aluno();
		aluno.setPessoaId(alunoDomain.getPessoa().getId());
		aluno.setMatricula(alunoDomain.getMatricula());
		aluno.setAtivo(true);
		
//		When
		Aluno savedAluno = alunoController.update(aluno);
		
//		Then
		assertNotNull(savedAluno);
		assertNotNull(savedAluno.getMatricula());
		assertTrue(savedAluno.getAtivo());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnDelete() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		alunoController.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Aluno aluno = new Aluno();
		aluno.setId(1);
		
//		When
		alunoController.delete(aluno);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentAluno() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		br.ufpr.domain.Aluno alunoDomain = createAndSaveAluno(false);
		Aluno aluno = new Aluno();
		aluno.setPessoaId(alunoDomain.getPessoa().getId());
		aluno.setMatricula(alunoDomain.getMatricula());
		
//		When
		alunoController.delete(aluno);
		
//		Then
		br.ufpr.domain.Aluno deletedAluno = alunoRepository.findOne(aluno.getId());
		assertTrue(deletedAluno.isDeleted());
		assertFalse(deletedAluno.isAtivo());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseExceptionGivenNullParameterSuppliedOnFind() throws NullParameterException, NoResultFoundException {
//		When
		alunoController.find(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseExceptionGivenInvalidIdOnFind() throws NullParameterException, NoResultFoundException {
//		When
		alunoController.find(1);
		
//		Then exception
	}
	
	@Test
	public void shouldFindAlunoGivenValidId() throws NullParameterException, NoResultFoundException {
//		Given
		br.ufpr.domain.Aluno aluno = createAndSaveAluno(false);
		
//		When
		Aluno alunoFound = alunoController.find(aluno.getId());
		
//		Then
		assertNotNull(alunoFound);
		assertEquals(aluno.getId().intValue(), alunoFound.getId().intValue());
		assertEquals(aluno.isAtivo(), alunoFound.getAtivo());
		assertNotNull(alunoFound.getPessoaId());
		assertEquals(aluno.getPessoa().getId(), alunoFound.getPessoaId());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseExceptionGivenNullCpf() throws NullParameterException, NoResultFoundException {
//		When
		alunoController.findByCpf("");
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseExceptionGivenInexistentAluno() throws NullParameterException, NoResultFoundException {
//		When
		alunoController.findByCpf(savedPessoa.getCpf() + "00");
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseExceptionGivenInexistentCpf() throws NullParameterException, NoResultFoundException {
//		Given
		createAndSaveAluno(true);
		
//		When
		alunoController.findByCpf(savedPessoa.getCpf() + "00");
	}
	
	@Test
	public void shouldFindExistentAlunoByCpf() throws NullParameterException, NoResultFoundException {
//		Given
		br.ufpr.domain.Aluno aluno = createAndSaveAluno(true);
		
//		When
		Aluno alunoFound = alunoController.findByCpf(savedPessoa.getCpf());
		
//		Then
		assertNotNull(alunoFound);
		assertEquals(aluno.getMatricula(), alunoFound.getMatricula());
	}
	
	@Test
	public void shouldCreateAlunoForPessoaWithCpf() throws NullParameterException, NoResultFoundException {
		
//		When
		Aluno alunoFound = alunoController.findByCpf(savedPessoa.getCpf());
		
//		Then
		assertNotNull(alunoFound);
		assertEquals(alunoFound.getCpf(), savedPessoa.getCpf());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseExceptionGivenNullNome() throws NullParameterException, NoResultFoundException {
//		When
		alunoController.findByNome(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseExceptionGivenInexistentAlunoWithNome() throws NullParameterException, NoResultFoundException {
//		When
		alunoController.findByNome("invalid");
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseExceptionGivenInexistentNome() throws NullParameterException, NoResultFoundException {
//		Given
		createAndSaveAluno(true);
		
//		When
		alunoController.findByNome("invalid");
	}
	
	@Test
	public void shouldFindExistentAlunoByNome() throws NullParameterException, NoResultFoundException {
//		Given
		br.ufpr.domain.Aluno aluno = createAndSaveAluno(true);
		
//		When
		Aluno alunoFound = alunoController.findByNome(savedPessoa.getNome());
		
//		Then
		assertNotNull(alunoFound);
		assertEquals(aluno.getMatricula(), alunoFound.getMatricula());
	}
	
	@Test
	public void shouldCreateAlunoForPessoaWithNome() throws NullParameterException, NoResultFoundException {
		
//		When
		Aluno alunoFound = alunoController.findByNome(savedPessoa.getNome());
		
//		Then
		assertNotNull(alunoFound);
		assertEquals(alunoFound.getNome(), savedPessoa.getNome());
	}
	
	private br.ufpr.domain.Aluno createAndSaveAluno(boolean isActive) {
		br.ufpr.domain.Aluno aluno = new br.ufpr.domain.Aluno();
		aluno.setPessoa(savedPessoa);
		aluno.setAtivo(isActive);
		alunoRepository.saveAndFlush(aluno);
		return aluno;
	}
	
}
