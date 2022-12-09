package com.luisjav.reto.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoContentException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoContentException(String message) {
		super(message);
	}
}
