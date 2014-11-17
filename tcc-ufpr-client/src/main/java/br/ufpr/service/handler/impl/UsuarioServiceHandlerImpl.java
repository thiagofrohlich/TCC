package br.ufpr.service.handler.impl;

import org.springframework.stereotype.Service;

import br.ufpr.model.Usuario;
import br.ufpr.service.handler.UsuarioServiceHandler;
import br.ufpr.wrapper.UsuarioWrapper;
import br.ufpr.wrapper.Wrapper;

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
	
	public Usuario getByCpf(String cpf) {
		return  (Usuario) getRestTemplate().getForObject(getPath()+"/{cpf}", Usuario.class, cpf);
	}

	@Override
	public Usuario findByCpf(String cpf) {
		return getRestTemplate().getForObject(getPath()+"/cpf/{cpf}", getReturnClass(), cpf);
	}

	@Override
	public UsuarioWrapper findByNome(String nome) {
		return (UsuarioWrapper) getRestTemplate().getForObject(getPath()+"/nome/{nome}", Wrapper.class, nome);
	}
	
}
