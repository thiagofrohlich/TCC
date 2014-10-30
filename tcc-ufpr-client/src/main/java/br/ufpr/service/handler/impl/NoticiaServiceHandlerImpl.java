package br.ufpr.service.handler.impl;

import org.springframework.stereotype.Service;

import br.ufpr.model.Noticia;
import br.ufpr.service.handler.NoticiaServiceHandler;

@Service
public class NoticiaServiceHandlerImpl extends AbstractServiceHandler<Noticia, Integer> implements NoticiaServiceHandler {

	@Override
	public String getRelativePath() {
		return "/noticia";
	}

}
