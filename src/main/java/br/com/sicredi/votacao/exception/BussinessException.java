package br.com.sicredi.votacao.exception;

public class BussinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BussinessException(String msg) {
		super(msg);
	}
}
