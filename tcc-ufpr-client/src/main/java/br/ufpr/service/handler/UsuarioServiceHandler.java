package br.ufpr.service.handler;

import br.ufpr.model.Usuario;
import br.ufpr.wrapper.UsuarioWrapper;

public interface UsuarioServiceHandler extends ServiceHandler<Usuario, Integer>{

	String encodePassword(String password);
	
	Boolean canLogin(String login, String password);
	
	Usuario findByCpf(String cpf);
	
	UsuarioWrapper findByNome(String nome);
	
}
