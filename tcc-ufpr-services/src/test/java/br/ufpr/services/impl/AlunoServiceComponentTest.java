package br.ufpr.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Aluno;
import br.ufpr.exception.MissingIdException;
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
		Aluno aluno = createAndSaveAluno();
		aluno.setAtivo(true);
		
//		When
		Aluno savedAluno = alunoService.update(aluno);
		
//		Then
		assertNotNull(savedAluno);
		assertNotNull(savedAluno.getMatricula());
		assertTrue(savedAluno.isAtivo());
	}
	
	private Aluno createAndSaveAluno() {
		br.ufpr.domain.Aluno aluno = new br.ufpr.domain.Aluno();
		aluno.setPessoa(savedPessoa);
		aluno.setAtivo(false);
		alunoRepository.saveAndFlush(aluno);
		return aluno;
	}
	
}
