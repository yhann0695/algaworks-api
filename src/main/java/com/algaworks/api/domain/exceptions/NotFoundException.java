package com.algaworks.api.domain.exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4840293511058462485L;

	public NotFoundException(String msg) {
		super(msg);
	}
}
