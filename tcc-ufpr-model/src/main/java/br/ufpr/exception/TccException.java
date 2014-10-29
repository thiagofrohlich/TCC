package br.ufpr.exception;

public class TccException extends Exception {

	private static final long serialVersionUID = 1L;

	public TccException(String string) {
		super(string);
	}
	
	public TccException(String string, Throwable exception) {
		super(string, exception);
	}
	
}
