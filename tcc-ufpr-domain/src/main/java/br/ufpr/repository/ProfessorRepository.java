package br.ufpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.domain.Pessoa;
import br.ufpr.domain.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

	Professor findByPessoa(Pessoa pessoa);
	
}
