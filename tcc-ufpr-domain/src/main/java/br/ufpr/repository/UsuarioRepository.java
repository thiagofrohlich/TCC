package br.ufpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.domain.Pessoa;
import br.ufpr.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByLogin(String login);
	
	Usuario findByPessoa(Pessoa pessoa);
	
}
