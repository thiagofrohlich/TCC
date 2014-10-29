package br.ufpr.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Aluno;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.repository.AlunoRepository;
import br.ufpr.support.PessoaTestSupport;

public class AlunoServiceComponentTest extends PessoaTestSupport {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AlunoRepository alunoRepository;

	@Test
	public void shouldInsertNewValidAluno() {
//		Given
		Aluno aluno = new Aluno();
		aluno.setPessoa(savedPessoa);
		aluno.setAtivo(false);
		
//		When
		Aluno savedAluno = alunoService.create(aluno);
		
//		Then
		assertNotNull(savedAluno);
		assertNotNull(savedAluno.getMatricula());
		assertNotNull(savedAluno.getPessoa());
		assertSame(aluno.getPessoa(), savedAluno.getPessoa());
		assertFalse(aluno.isAtivo());
	}
	
	@Test
	public void shouldUpdateExistentAluno() throws MissingIdException {
//		Given
		Aluno aluno = createAndSaveAluno(false);
		aluno.setAtivo(true);
		
//		When
		Aluno savedAluno = alunoService.update(aluno);
		
//		Then
		assertNotNull(savedAluno);
		assertNotNull(savedAluno.getMatricula());
		assertTrue(savedAluno.isAtivo());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameter() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		alunoService.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		alunoService.delete(1);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentAluno() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Aluno aluno = createAndSaveAluno(true);
		
//		When
		alunoService.delete(aluno.getId());
		
//		Then
		Aluno deletedAluno = alunoRepository.findOne(aluno.getId());
		assertTrue(deletedAluno.isDeleted());
		assertFalse(deletedAluno.isAtivo());
	}
	
	@Test
	public void shouldFindAlunoGivenValidId() {
//		Given
		Aluno aluno = createAndSaveAluno(false);
		
//		When
		Aluno alunoFound = alunoService.find(aluno.getId());
		
//		Then
		assertNotNull(alunoFound);
		assertSame(aluno, alunoFound);
	}
	
	private Aluno createAndSaveAluno(boolean isActive) {
		br.ufpr.domain.Aluno aluno = new br.ufpr.domain.Aluno();
		aluno.setPessoa(savedPessoa);
		aluno.setAtivo(isActive);
		alunoRepository.saveAndFlush(aluno);
		return aluno;
	}
	
}
