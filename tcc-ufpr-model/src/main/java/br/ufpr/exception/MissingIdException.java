package br.ufpr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingIdException extends TccException {

	private static final long serialVersionUID = 1L;
	
	public MissingIdException() {
		super("The given id must not be null!");
	}

	public MissingIdException(String message, Throwable exception) {
		super(message, exception);
	}
	
}
