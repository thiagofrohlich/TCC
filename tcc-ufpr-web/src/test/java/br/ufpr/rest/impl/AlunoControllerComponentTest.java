package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.exception.MissingIdException;
import br.ufpr.model.Aluno;
import br.ufpr.repository.AlunoRepository;
import br.ufpr.support.PessoaTestSupport;

public class AlunoControllerComponentTest extends PessoaTestSupport {

	@Autowired
	private AlunoController alunoController;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
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
	
	@Test
	public void shouldUpdateExistentAluno() throws MissingIdException {
//		Given
		br.ufpr.domain.Aluno alunoDomain = createAndSaveAluno();
		Aluno aluno = new Aluno();
		aluno.setPessoaId(alunoDomain.getPessoa().getId());
		aluno.setMatricula(alunoDomain.getMatricula());
		aluno.setAtivo(true);
		
//		When
		Aluno savedAluno = alunoController.update(aluno);
		
//		Then
		assertNotNull(savedAluno);
		assertNotNull(savedAluno.getMatricula());
		assertTrue(savedAluno.isAtivo());
	}
	
	private br.ufpr.domain.Aluno createAndSaveAluno() {
		br.ufpr.domain.Aluno aluno = new br.ufpr.domain.Aluno();
		aluno.setPessoa(savedPessoa);
		aluno.setAtivo(false);
		alunoRepository.saveAndFlush(aluno);
		return aluno;
	}
	
}
