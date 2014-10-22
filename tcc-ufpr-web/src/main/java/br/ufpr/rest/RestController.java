package br.ufpr.rest;

import br.ufpr.model.BusinessModel;

public interface RestController<M extends BusinessModel> {

	M create(M model);
	
}
