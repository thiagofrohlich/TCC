package br.ufpr.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Aluno;
import br.ufpr.domain.Pessoa;
import br.ufpr.support.SpringTestSupport;

public class AlunoRepositoryTest extends SpringTestSupport {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	private Pessoa validPessoa;
	
	@Before
	public void createValidPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("fake name");
		pessoa.setCpf("123456789-00");
		validPessoa = pessoaRepository.save(pessoa);
		
	}
	
	@Test
	public void shouldInsertNewValidPessoa() {
//		Given
		Aluno aluno = new Aluno();
		aluno.setPessoa(validPessoa);
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
