package br.ufpr.exception;

public class NullParameterException extends TccException {

	private static final long serialVersionUID = 1L;
	
	public NullParameterException() {
		super("The given parameter must not be null!");
	}

	public NullParameterException(String message, Throwable exception) {
		super(message, exception);
	}
	
}
