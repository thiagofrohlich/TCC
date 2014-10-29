package br.ufpr.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpr.domain.Noticia;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.repository.NoticiaRepository;
import br.ufpr.services.AbstractCrudService;
import br.ufpr.util.AssertUtils;

@Service
@Transactional
public class NoticiaService extends AbstractCrudService<Noticia, Integer> {

	@Autowired
	public NoticiaService(NoticiaRepository repository) {
		super(repository);
	}
	
	@Override
	public void delete(Integer id) throws NullParameterException,
			NotDeletedObjectException, NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(id);
		
		Noticia domainObject = find(id);
		AssertUtils.assertIsFound(domainObject);
		
		try {
			repository.delete(domainObject);
		} catch(Exception e) {
			throw new NotDeletedObjectException(e);
		}
	}

}
