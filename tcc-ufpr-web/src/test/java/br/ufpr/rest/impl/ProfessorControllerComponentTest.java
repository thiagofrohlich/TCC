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
import br.ufpr.model.Professor;
import br.ufpr.repository.ProfessorRepository;
import br.ufpr.support.PessoaTestSupport;

public class ProfessorControllerComponentTest extends PessoaTestSupport {

	@Autowired
	private ProfessorController professorController;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnCreate() throws MissingIdException, NullParameterException {
//		When
		professorController.create(null);
		
//		Then exception
	}
	
	@Test
	public void shouldInsertNewValidProfessor() throws NullParameterException {
//		Given
		Professor professor = new Professor();
		professor.setNome("fake name");
		professor.setCpf("123456789-00");
		
//		When
		Professor savedProfessor = professorController.create(professor);
		
//		Then
		assertNotNull(savedProfessor);
		assertNotNull(savedProfessor.getId());
		assertEquals(professor.getNome(), savedProfessor.getNome());
		assertEquals(professor.getCpf(), savedProfessor.getCpf());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnUpdate() throws MissingIdException, NullParameterException {
//		When
		professorController.update(null);
		
//		Then exception
	}
	
	@Test
	public void shouldUpdateExistentProfessor() throws MissingIdException, NullParameterException {
//		Given
		br.ufpr.domain.Professor professorDomain = createAndSaveProfessor(false);
		Professor professor = new Professor();
		professor.setPessoaId(professorDomain.getPessoa().getId());
		professor.setId(professorDomain.getId());
		professor.setAtivo(true);
		
//		When
		Professor savedProfessor = professorController.update(professor);
		
//		Then
		assertNotNull(savedProfessor);
		assertNotNull(savedProfessor.getId());
		assertTrue(savedProfessor.isAtivo());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnDelete() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		professorController.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Professor professor = new Professor();
		professor.setId(1);
		
//		When
		professorController.delete(professor);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentProfessor() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		br.ufpr.domain.Professor professorDomain = createAndSaveProfessor(false);
		Professor professor = new Professor();
		professor.setPessoaId(professorDomain.getPessoa().getId());
		professor.setId(professorDomain.getId());
		
//		When
		professorController.delete(professor);
		
//		Then
		br.ufpr.domain.Professor deletedProfessor = professorRepository.findOne(professor.getId());
		assertTrue(deletedProfessor.isDeleted());
		assertFalse(deletedProfessor.isAtivo());
	}
	
	private br.ufpr.domain.Professor createAndSaveProfessor(boolean isActive) {
		br.ufpr.domain.Professor professor = new br.ufpr.domain.Professor();
		professor.setPessoa(savedPessoa);
		professor.setAtivo(isActive);
		professorRepository.saveAndFlush(professor);
		return professor;
	}
	
}
