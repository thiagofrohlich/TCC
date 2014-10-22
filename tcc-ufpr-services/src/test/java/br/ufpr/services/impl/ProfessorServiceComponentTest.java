package br.ufpr.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Professor;
import br.ufpr.support.PessoaTestSupport;

public class ProfessorServiceComponentTest extends PessoaTestSupport {

	@Autowired
	private ProfessorService professorService;

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
	
}
