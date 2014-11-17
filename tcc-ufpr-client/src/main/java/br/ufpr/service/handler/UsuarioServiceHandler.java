package br.ufpr.service.handler;

import java.util.List;

import br.ufpr.model.Usuario;

public interface UsuarioServiceHandler extends ServiceHandler<Usuario, Integer>{

	String encodePassword(String password);
	
	Boolean canLogin(String login, String password);
	
	Usuario findByCpf(String cpf);
	
	List<Usuario> findByNome(String nome);
	
}
