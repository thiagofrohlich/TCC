package br.ufpr.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Disciplina;
import br.ufpr.domain.Pessoa;
import br.ufpr.domain.Professor;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.repository.DisciplinaRepository;
import br.ufpr.repository.PessoaRepository;
import br.ufpr.repository.ProfessorRepository;
import br.ufpr.services.CrudService;
import br.ufpr.support.SpringTestSupport;

public class DisciplinaServiceComponentTest extends SpringTestSupport {

	@Autowired
	private CrudService<Disciplina, Integer> disciplinaService;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void shouldInsertNewValidDisciplina() {
//		Given
		Disciplina disciplina = new Disciplina();
		disciplina.setTurno("N");
		disciplina.setProfessor(createProfessor());
		disciplina.setNome("fake name");
		disciplina.setPeriodo(1);
		
//		When
		Disciplina savedDisciplina = disciplinaService.create(disciplina);
		
//		Then
		assertNotNull(savedDisciplina);
		assertNotNull(savedDisciplina.getId());
		assertEquals(disciplina.getNome(), savedDisciplina.getNome());
		assertEquals(disciplina.getPeriodo(), savedDisciplina.getPeriodo());
		assertEquals(disciplina.getTurno(), savedDisciplina.getTurno());
	}
	
	@Test
	public void shouldUpdateExistentDisciplina() throws MissingIdException {
//		Given
		Disciplina disciplina = createAndSaveDisciplina();
		disciplina.setTurno("T");
		
//		When
		Disciplina savedDisciplina = disciplinaService.update(disciplina);
		
//		Then
		assertNotNull(savedDisciplina);
		assertNotNull(savedDisciplina.getId());
		assertEquals(disciplina.getTurno(), savedDisciplina.getTurno());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameter() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		disciplinaService.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		disciplinaService.delete(1);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentDisciplina() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Disciplina disciplina = createAndSaveDisciplina();
		
//		When
		disciplinaService.delete(disciplina.getId());
		
//		Then
		Disciplina deletedDisciplina = disciplinaRepository.findOne(disciplina.getId());
		assertNull(deletedDisciplina);
	}
	
	@Test
	public void shouldFindDisciplinaGivenValidId() {
//		Given
		Disciplina disciplina = createAndSaveDisciplina();
		
//		When
		Disciplina disciplinaFound = disciplinaService.find(disciplina.getId());
		
//		Then
		assertNotNull(disciplinaFound);
		assertSame(disciplina, disciplinaFound);
	}
	
	private Disciplina createAndSaveDisciplina() {
		br.ufpr.domain.Disciplina disciplina = new br.ufpr.domain.Disciplina();
		disciplina.setTurno("N");
		disciplina.setProfessor(createProfessor());
		disciplinaRepository.saveAndFlush(disciplina);
		return disciplina;
	}
	
	private Professor createProfessor() {
		Professor professor = new Professor();
		professor.setPessoa(createPessoa());
		professor.setAtivo(true);
		professorRepository.saveAndFlush(professor);
		return professor;
	}
	
	private Pessoa createPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("fake name");
		pessoa.setCpf("123456789-00");
		return pessoaRepository.saveAndFlush(pessoa);
	}
	
}
