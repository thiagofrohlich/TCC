package br.ufpr.services.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpr.domain.Usuario;
import br.ufpr.repository.UsuarioRepository;
import br.ufpr.services.AbstractCrudService;
import br.ufpr.services.UserService;

@Service
@Transactional
public class UsuarioService extends AbstractCrudService<Usuario, Integer> implements UserService {

	private static final String SECURITY_TYPE = "MD5";

	@Autowired
	public UsuarioService(UsuarioRepository repository) {
		super(repository);
	}

	@Override
	public String encodePassword(String password) {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
	        md = MessageDigest.getInstance(SECURITY_TYPE);
	        md.reset();
	        byte[] digested = md.digest(password.getBytes());
	        for(int i = 0; i < digested.length; i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	    } catch (NoSuchAlgorithmException ex) {
	    	throw new RuntimeException(ex);
	    }
		return sb.toString();
	}

	@Override
	public boolean canLogin(String login, String password) {
		return false;
	}

}
