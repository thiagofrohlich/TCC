package br.ufpr.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Aluno;
import br.ufpr.domain.Professor;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.repository.ProfessorRepository;
import br.ufpr.support.PessoaTestSupport;

public class ProfessorServiceComponentTest extends PessoaTestSupport {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private ProfessorRepository professorRepository;

	@Test
	public void shouldInsertNewValidProfessor() {
//		Given
		Professor professor = new Professor();
		professor.setPessoa(savedPessoa);
		professor.setAtivo(false);
		
//		When
		Professor savedProfessor = professorService.create(professor);
		
//		Then
		assertNotNull(savedProfessor);
		assertNotNull(savedProfessor.getId());
		assertNotNull(savedProfessor.getPessoa());
		assertSame(professor.getPessoa(), savedProfessor.getPessoa());
		assertFalse(professor.isAtivo());
	}
	
	@Test
	public void shouldUpdateExistentProfessor() throws MissingIdException {
//		Given
		Professor professor = createAndSaveProfessor(false);
		professor.setAtivo(true);
		
//		When
		Professor savedProfessor = professorService.update(professor);
		
//		Then
		assertNotNull(savedProfessor);
		assertNotNull(savedProfessor.getId());
		assertTrue(savedProfessor.isAtivo());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameter() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		professorService.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		professorService.delete(1);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentProfessor() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Professor professor = createAndSaveProfessor(true);
		
//		When
		professorService.delete(professor.getId());
		
//		Then
		Professor deletedProfessor = professorRepository.findOne(professor.getId());
		assertTrue(deletedProfessor.isDeleted());
		assertFalse(deletedProfessor.isAtivo());
	}
	
	@Test
	public void shouldFindAlunoGivenValidId() {
//		Given
		Professor professor = createAndSaveProfessor(false);
		
//		When
		Professor professorFound = professorService.find(professor.getId());
		
//		Then
		assertNotNull(professorFound);
		assertSame(professor, professorFound);
	}
	
	private Professor createAndSaveProfessor(boolean isActive) {
		Professor professor = new Professor();
		professor.setPessoa(savedPessoa);
		professor.setAtivo(isActive);
		professorRepository.saveAndFlush(professor);
		return professor;
	}
	
}
