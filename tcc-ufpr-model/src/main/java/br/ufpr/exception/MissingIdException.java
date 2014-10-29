package br.ufpr.exception;

public class MissingIdException extends TccException {

	private static final long serialVersionUID = 1L;
	
	public MissingIdException() {
		super("The given id must not be null!");
	}

	public MissingIdException(String message, Throwable exception) {
		super(message, exception);
	}
	
}
