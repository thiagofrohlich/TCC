package br.ufpr.services;

import br.ufpr.domain.Usuario;

public interface UserService {

	String encodePassword(String password);
	
	boolean canLogin(String login, String password);
	
	Usuario findByLogin(String login);
	
}
