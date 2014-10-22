package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.model.Professor;
import br.ufpr.support.SpringTestSupport;

public class ProfessorControllerComponentTest extends SpringTestSupport {

	@Autowired
	private ProfessorController professorController;
	
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
	
}
