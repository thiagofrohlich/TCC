package br.ufpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.domain.Aluno;
import br.ufpr.domain.Pessoa;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	Aluno findByPessoa(Pessoa pessoa);
	
}
