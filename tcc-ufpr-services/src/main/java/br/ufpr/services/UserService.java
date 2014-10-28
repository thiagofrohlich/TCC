package br.ufpr.services;

public interface UserService {

	String encodePassword(String password);
	
	boolean canLogin(String login, String password);
	
}
