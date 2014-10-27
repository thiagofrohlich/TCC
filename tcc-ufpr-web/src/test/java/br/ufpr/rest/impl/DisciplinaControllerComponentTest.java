package br.ufpr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Pessoa;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.model.Disciplina;
import br.ufpr.model.Professor;
import br.ufpr.model.Turno;
import br.ufpr.repository.DisciplinaRepository;
import br.ufpr.repository.PessoaRepository;
import br.ufpr.repository.ProfessorRepository;
import br.ufpr.support.SpringTestSupport;

public class DisciplinaControllerComponentTest extends SpringTestSupport {

	@Autowired
	private DisciplinaController disciplinaController;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnCreate() throws MissingIdException, NullParameterException {
//		When
		disciplinaController.create(null);
		
//		Then exception
	}
	
	@Test
	public void shouldInsertNewValidDisciplina() throws NullParameterException {
//		Given
		br.ufpr.domain.Professor createdProfessor = createProfessor();
		Disciplina disciplina = new Disciplina();
		disciplina.setNome("fake name");
		disciplina.setTurno(Turno.N);
		disciplina.setPeriodo(1);
		disciplina.setProfessor(new Professor());
		disciplina.getProfessor().setId(createdProfessor.getId());

//		When
		Disciplina savedDisciplina = disciplinaController.create(disciplina);
		
//		Then
		assertNotNull(savedDisciplina);
		assertNotNull(savedDisciplina.getId());
		assertEquals(disciplina.getNome(), savedDisciplina.getNome());
		assertEquals(disciplina.getPeriodo(), savedDisciplina.getPeriodo());
		assertEquals(disciplina.getTurno(), savedDisciplina.getTurno());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnUpdate() throws MissingIdException, NullParameterException {
//		When
		disciplinaController.update(null);
		
//		Then exception
	}
	
	@Test
	public void shouldUpdateExistentDisciplina() throws MissingIdException, NullParameterException {
//		Given
		br.ufpr.domain.Professor createdProfessor = createProfessor();
		br.ufpr.domain.Disciplina disciplinaDomain = createAndSaveDisciplina();
		Disciplina disciplina = new Disciplina();
		disciplina.setId(disciplinaDomain.getId());
		disciplina.setTurno(Turno.T);
		disciplina.setProfessor(new Professor());
		disciplina.getProfessor().setId(createdProfessor.getId());
		
//		When
		Disciplina savedDisciplina = disciplinaController.update(disciplina);
		
//		Then
		assertNotNull(savedDisciplina);
		assertNotNull(savedDisciplina.getId());
		assertEquals(Turno.T, savedDisciplina.getTurno());
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseNullParameterExceptionGivenNullParameterOnDelete() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		When
		disciplinaController.delete(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseNoResultFoundExceptionGivenInvalidId() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		Disciplina disciplina = new Disciplina();
		disciplina.setId(1);
		
//		When
		disciplinaController.delete(disciplina);
		
//		Then exception
	}
	
	@Test
	public void shouldDeleteExistentDisciplina() throws NullParameterException, NotDeletedObjectException, NoResultFoundException {
//		Given
		br.ufpr.domain.Disciplina disciplinaDomain = createAndSaveDisciplina();
		Disciplina disciplina = new Disciplina();
		disciplina.setId(disciplinaDomain.getId());
		disciplina.setTurno(Turno.T);
		
//		When
		disciplinaController.delete(disciplina);
		
//		Then
		br.ufpr.domain.Disciplina deletedDisciplina = disciplinaRepository.findOne(disciplina.getId());
		assertNull(deletedDisciplina);
	}
	
	@Test(expected=NullParameterException.class)
	public void shouldRaiseExceptionGivenNullParameterSuppliedOnFind() throws NullParameterException, NoResultFoundException {
//		When
		disciplinaController.find(null);
		
//		Then exception
	}
	
	@Test(expected=NoResultFoundException.class)
	public void shouldRaiseExceptionGivenInvalidIdOnFind() throws NullParameterException, NoResultFoundException {
//		When
		disciplinaController.find(1);
		
//		Then exception
	}
	
	@Test
	public void shouldFindDisciplinaGivenValidId() throws NullParameterException, NoResultFoundException {
//		Given
		br.ufpr.domain.Disciplina disciplina = createAndSaveDisciplina();
		
//		When
		Disciplina disciplinaFound = disciplinaController.find(disciplina.getId());
		
//		Then
		assertNotNull(disciplinaFound);
		assertEquals(disciplina.getId().intValue(), disciplinaFound.getId().intValue());
		assertEquals(disciplina.getNome(), disciplinaFound.getNome());
		assertEquals(disciplina.getPeriodo(), disciplinaFound.getPeriodo());
		assertEquals(disciplina.getTurno(), disciplinaFound.getTurno().getKey());
	}
	
	private br.ufpr.domain.Disciplina createAndSaveDisciplina() {
		br.ufpr.domain.Disciplina disciplina = new br.ufpr.domain.Disciplina();
		disciplina.setTurno("N");
		disciplina.setProfessor(createProfessor());
		disciplinaRepository.saveAndFlush(disciplina);
		return disciplina;
	}
	
	private br.ufpr.domain.Professor createProfessor() {
		br.ufpr.domain.Professor professor = new br.ufpr.domain.Professor();
		professor.setPessoa(createPessoa());
		professor.setAtivo(true);
		professorRepository.saveAndFlush(professor);
		return professor;
	}
	
	private br.ufpr.domain.Pessoa createPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("fake name");
		pessoa.setCpf("123456789-00");
		return pessoaRepository.saveAndFlush(pessoa);
	}
	
}
