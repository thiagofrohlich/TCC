package br.ufpr.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Disciplina;
import br.ufpr.domain.Professor;
import br.ufpr.support.PessoaTestSupport;

public class DisciplinaRepositoryTest extends PessoaTestSupport {

	private Professor professor;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Before
	public void saveProfessor() {
		Professor professor = new Professor();
		professor.setAtivo(true);
		professor.setPessoa(savedPessoa);
		this.professor = professorRepository.saveAndFlush(professor);
	}
	
	@Test
	public void shouldInsertDisciplina() {
//		Given
		Disciplina disciplina = new Disciplina();
		disciplina.setProfessor(professor);
		disciplina.setNome("disciplina");
		disciplina.setTurno("T");
		disciplina.setPeriodo(1);
		
//		When
		Disciplina savedDisciplina = disciplinaRepository.saveAndFlush(disciplina);
		
//		Then
		assertNotNull(savedDisciplina);
		assertNotNull(savedDisciplina.getId());
		assertSame(professor, savedDisciplina.getProfessor());
		assertEquals(disciplina.getNome(), savedDisciplina.getNome());
		assertEquals(disciplina.getTurno(), savedDisciplina.getTurno());
		assertEquals(disciplina.getPeriodo(), savedDisciplina.getPeriodo());
	}
	
}
