package br.ufpr.rest;

import java.io.Serializable;

import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;
import br.ufpr.model.BusinessModel;

public interface RestController<M extends BusinessModel, ID extends Serializable> {

	M create(M model) throws NullParameterException;

	M update(M model) throws MissingIdException, NullParameterException;
	
	void delete(M model) throws NullParameterException, NotDeletedObjectException, NoResultFoundException;
	
	M find(ID id) throws NullParameterException, NoResultFoundException;
	
}
