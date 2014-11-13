package br.ufpr.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Professor;
import br.ufpr.support.PessoaTestSupport;

public class ProfessorRepositoryTest extends PessoaTestSupport {

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Test
	public void shouldInsertNewValidProfessor() {
//		Given
		Professor professor = new Professor();
		professor.setPessoa(savedPessoa);
		professor.setAtivo(true);
		
//		When
		Professor savedProfessor = professorRepository.save(professor);
		
//		Then
		assertNotNull(savedProfessor);
		assertNotNull(savedProfessor.getId());
		assertNotNull(savedProfessor.getPessoa());
		assertSame(professor.getPessoa(), savedProfessor.getPessoa());
	}
	
	@Test
	public void shouldFindByPessoa() {
//		Given
		Professor professor = createProfessor();
		
//		When
		Professor professorFound = professorRepository.findByPessoa(savedPessoa);
		
//		Then
		assertNotNull(professorFound);
		assertSame(professor, professorFound);
	}

	private Professor createProfessor() {
		Professor professor = new Professor();
		professor.setPessoa(savedPessoa);
		professor.setAtivo(true);
		professorRepository.saveAndFlush(professor);
		return professor;
	}
	
}
