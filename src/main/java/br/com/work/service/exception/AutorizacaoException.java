package br.com.work.service.exception;

public class AutorizacaoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AutorizacaoException(String message) {
		super(message);
	}

}
