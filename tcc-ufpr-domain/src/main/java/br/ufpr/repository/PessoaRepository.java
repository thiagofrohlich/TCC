package br.ufpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Pessoa findByCpf(String cpf);
	
	Pessoa findByNomeContaining(String nome);
	
}
