package br.ufpr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class NotDeletedObjectException extends TccException {

	private static final String DEFAULT_MESSAGE = "The object could not be deleted!";
	private static final long serialVersionUID = 1L;
	
	public NotDeletedObjectException() {
		super(DEFAULT_MESSAGE);
	}

	public NotDeletedObjectException(String message) {
		super(message);
	}
	
	public NotDeletedObjectException(Throwable exception) {
		super(DEFAULT_MESSAGE, exception);
	}
	
	public NotDeletedObjectException(String message, Throwable exception) {
		super(message, exception);
	}
	
}
