package br.ufpr.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpr.domain.Disciplina;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.repository.DisciplinaRepository;
import br.ufpr.services.AbstractCrudService;
import br.ufpr.util.AssertUtils;

@Service
@Transactional
public class DisciplinaService extends AbstractCrudService<Disciplina, Integer> {

	@Autowired
	public DisciplinaService(DisciplinaRepository repository) {
		super(repository);
	}
	
	@Override
	public void delete(Integer id) throws NullParameterException,
			NotDeletedObjectException, NoResultFoundException {
		AssertUtils.assertParameterIsSupplied(id);
		
		Disciplina domainObject = find(id);
		AssertUtils.assertIsFound(domainObject);
		
		try {
			repository.delete(domainObject);
		} catch(Exception e) {
			throw new NotDeletedObjectException(e);
		}
	}

}
