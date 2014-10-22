package br.ufpr.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Aluno;
import br.ufpr.support.PessoaTestSupport;

public class AlunoServiceComponentTest extends PessoaTestSupport {

	@Autowired
	private AlunoService alunoService;

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
	
}
