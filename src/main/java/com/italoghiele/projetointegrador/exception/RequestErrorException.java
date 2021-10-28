package com.italoghiele.projetointegrador.exception;

import lombok.Getter;

@Getter
public class RequestErrorException extends RuntimeException {

	public RequestErrorException(String message) {
		super(message);
	}
}
