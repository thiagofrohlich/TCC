package br.ufpr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Pessoa findByCpf(String cpf);
	
	List<Pessoa> findByNomeContaining(String nome);
	
}
