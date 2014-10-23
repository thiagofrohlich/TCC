package br.ufpr.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpr.domain.Usuario;
import br.ufpr.repository.UsuarioRepository;
import br.ufpr.services.AbstractCrudService;

@Service
@Transactional
public class UsuarioService extends AbstractCrudService<Usuario, Integer> {

	@Autowired
	public UsuarioService(UsuarioRepository repository) {
		super(repository);
	}

}
