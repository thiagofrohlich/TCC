package br.ufpr.exception;

public class NoResultFoundException extends TccException {

	private static final long serialVersionUID = 1L;
	
	public NoResultFoundException() {
		super("No result found!");
	}

	public NoResultFoundException(String message, Throwable exception) {
		super(message, exception);
	}
	
}
