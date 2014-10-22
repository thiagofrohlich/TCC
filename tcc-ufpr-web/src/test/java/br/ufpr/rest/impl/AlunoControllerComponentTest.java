package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.model.Aluno;
import br.ufpr.support.SpringTestSupport;

public class AlunoControllerComponentTest extends SpringTestSupport {

	@Autowired
	private AlunoController alunoController;
	
	@Test
	public void shouldInsertNewValidAluno() {
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
	
}
