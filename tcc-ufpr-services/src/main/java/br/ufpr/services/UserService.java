package br.ufpr.services;

import br.ufpr.domain.Pessoa;
import br.ufpr.domain.Usuario;
import br.ufpr.exception.NoResultFoundException;

public interface UserService extends CrudService<Usuario, Integer> {

	String encodePassword(String password);
	
	boolean canLogin(String login, String password);
	
	Usuario findByLogin(String login);
	
	Usuario findByPessoa(Pessoa pessoa) throws NoResultFoundException;
	
}
