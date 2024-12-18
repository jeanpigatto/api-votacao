package br.com.sicredi.votacao.exception;

public class ErroGeralDTO {
	private int statusCode;
	private String erro;
	
	public ErroGeralDTO(int statusCode, String erro) {
		super();
		this.setStatusCode(statusCode);
		this.erro = erro;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getErro() {
		return erro;
	}
}
