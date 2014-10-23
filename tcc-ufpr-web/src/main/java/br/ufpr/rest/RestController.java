package br.ufpr.rest;

import br.ufpr.exception.MissingIdException;
import br.ufpr.model.BusinessModel;

public interface RestController<M extends BusinessModel> {

	M create(M model);

	M update(M model) throws MissingIdException;
	
}
