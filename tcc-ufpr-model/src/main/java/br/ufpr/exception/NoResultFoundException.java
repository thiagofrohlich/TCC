package br.ufpr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoResultFoundException extends TccException {

	private static final long serialVersionUID = 1L;
	
	public NoResultFoundException() {
		super("No result found!");
	}

	public NoResultFoundException(String message, Throwable exception) {
		super(message, exception);
	}
	
}
