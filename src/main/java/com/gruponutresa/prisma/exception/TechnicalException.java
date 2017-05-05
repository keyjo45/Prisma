package com.gruponutresa.prisma.exception;

public class TechnicalException extends Exception{
	

	public TechnicalException(String message) {
		super(message);
	}
	
	public TechnicalException(String message, Exception e) {
		super(message,e);
	}

}
