package br.ufpr.tcc.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


@Component
public class TccAuthenticationProvider  implements AuthenticationProvider{
	
//	private UsuarioServiceHandler usuarioServiceHandler = new UsuarioServiceHandler();

	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String login = (String) authentication.getName();
		String password = (String) authentication.getCredentials();
		
////	UsuarioSummary usuario = usuarioServiceHandler.getByLogin(login);
//		throwExceptionIfNotFound(usuario);
//		throwExceptionIfPasswordIsInvalid(password, usuario);
		
//		
		Collection<GrantedAuthority> authorities = getAuthorities("USUARIO");
		
		return new UsernamePasswordAuthenticationToken(login, password, authorities);
	}

	private Collection<GrantedAuthority> getAuthorities(String acesso) {
//		String acessos[] = usuario.getAcessos().split(",");
		Collection<GrantedAuthority> authorities = new ArrayList<>();
//		for(String acesso : acessos) {
			authorities.add(new Role(acesso));
//		}
		return authorities;
	}

	/*private void throwExceptionIfPasswordIsInvalid(String password,
			UsuarioSummary usuario) {
		if(!password.equals(usuario.getSenha())) {
			throw new BadCredentialsException("Senha inválida");
		}
	}

	private void throwExceptionIfNotFound(UsuarioSummary usuario) {
		if(usuario == null) {
			throw new BadCredentialsException("Usuário não encontrado.");
		}
	}
*/
	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
