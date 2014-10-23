package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.exception.MissingIdException;
import br.ufpr.model.Professor;
import br.ufpr.repository.ProfessorRepository;
import br.ufpr.support.PessoaTestSupport;

public class ProfessorControllerComponentTest extends PessoaTestSupport {

	@Autowired
	private ProfessorController professorController;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Test
	public void shouldInsertNewValidProfessor() {
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
	
	@Test
	public void shouldUpdateExistentProfessor() throws MissingIdException {
//		Given
		br.ufpr.domain.Professor professorDomain = createAndSaveProfessor();
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
	
	private br.ufpr.domain.Professor createAndSaveProfessor() {
		br.ufpr.domain.Professor professor = new br.ufpr.domain.Professor();
		professor.setPessoa(savedPessoa);
		professor.setAtivo(false);
		professorRepository.saveAndFlush(professor);
		return professor;
	}
	
}
