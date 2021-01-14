package com.algaworks.api.domain.exceptions;

public class NegocioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7579473225756027687L;

	
	public NegocioException(String msg) {
		super(msg);
	}
}
