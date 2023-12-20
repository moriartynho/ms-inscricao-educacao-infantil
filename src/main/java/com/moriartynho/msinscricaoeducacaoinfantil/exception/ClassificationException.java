package com.moriartynho.msinscricaoeducacaoinfantil.exception;

public class ClassificationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ClassificationException(String msg) {
		super(msg);
	}

	public ClassificationException(String msg, Throwable e) {
		super(msg, e);
	}

}
