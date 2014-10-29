package br.ufpr.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Aluno;
import br.ufpr.support.PessoaTestSupport;

public class AlunoRepositoryTest extends PessoaTestSupport {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Test
	public void shouldInsertNewValidAluno() {
//		Given
		Aluno aluno = new Aluno();
		aluno.setPessoa(savedPessoa);
		aluno.setAtivo(true);
		
//		When
		Aluno savedAluno = alunoRepository.save(aluno);
		
//		Then
		assertNotNull(savedAluno);
		assertNotNull(savedAluno.getMatricula());
		assertNotNull(savedAluno.getPessoa());
		assertSame(aluno.getPessoa(), savedAluno.getPessoa());
		assertTrue(aluno.isAtivo());
	}
	
}
