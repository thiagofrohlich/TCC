package br.ufpr.service.handler.impl;

import org.springframework.stereotype.Service;

import br.ufpr.model.Usuario;
import br.ufpr.service.handler.UsuarioServiceHandler;

@Service
public class UsuarioServiceHandlerImpl extends AbstractServiceHandler<Usuario, Integer> implements UsuarioServiceHandler {

	@Override
	public String getRelativePath() {
		return "/usuario";
	}

	@Override
	public String encodePassword(String password) {
		return getRestTemplate().getForObject(getPath()+"/password/encode/{password}", String.class, password);
	}

	@Override
	public boolean canLogin(String login, String password) {
		return getRestTemplate().getForObject(getPath()+"/login/{login}/{password}", Boolean.class, login, password);
	}

	@Override
	public Usuario findByCpf(String cpf) {
		return getRestTemplate().getForObject(getPath()+"/cpf/{cpf}", getReturnClass(), cpf);
	}

	@Override
	public Usuario findByNome(String nome) {
		return getRestTemplate().getForObject(getPath()+"/nome/{nome}", getReturnClass(), nome);
	}
	
}
